import os
import cv2
import torch 
from kafka import KafkaConsumer
from ultralytics import YOLO
import numpy as np
from ai_kafka_producer import send_frame
from ultralytics.utils.nms import non_max_suppression

model = YOLO("yolov8m.pt")
model.to("cuda")
model.predict(np.zeros((640, 640, 3), dtype=np.uint8))
model.model.half()

frame_consumer = KafkaConsumer(
    'c_stream', 
    bootstrap_servers=['localhost:29092'],
    auto_offset_reset='latest',
    enable_auto_commit=True,
    group_id='yolo-ai'
)

print ("YOLOv8 Consumer is running...")

def infer_fp16(frame, conf_thres=0.25, iou_thres=0.45):
    img = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

    tensor = torch.from_numpy(img).to("cuda").half()
    tensor = tensor.permute(2, 0, 1).contiguous().unsqueeze(0)
    tensor = tensor / 255.0

    _, _, h, w = tensor.shape
    new_h = (h + 31) // 32 * 32
    new_w = (w + 31) // 32 * 32

    padded = torch.zeros((1, 3, new_h, new_w), dtype=torch.half, device="cuda")
    padded[:, :, :h, :w] = tensor
    tensor = padded

    preds = model.model(tensor)

    results = non_max_suppression(preds, conf_thres=conf_thres, iou_thres=iou_thres)
    if len(results) > 0:
        dets = results[0]
        class_ids = dets[:, 5].int().cpu().numpy()
        boxes = dets[:, :4].cpu().numpy()
        confs = dets[:, 4].cpu().numpy()

        class Box:
            def __init__(self, xyxy, conf, cls):
                self.xyxy = [xyxy]
                self.conf = [conf]
                self.cls = [cls]

        class Result:
            def __init__(self):
                self.boxes = []
                self.names = model.names

        result_obj = Result()
        for b, c, cf in zip(boxes, class_ids, confs):
            result_obj.boxes.append(Box(b, cf, c))
        return result_obj
    else:
        class Result:
            def __init__(self):
                self.boxes = []
                self.names = model.names
        return Result()

for msg in frame_consumer:
    jpeg_bytes = msg.value
    np_img = np.frombuffer(jpeg_bytes, dtype=np.uint8)
    frame = cv2.imdecode(np_img, cv2.IMREAD_COLOR)

    if frame is None:
        print("Failed to decode image")
        continue

    print("Received frame for inference")

    try:
        result = infer_fp16(frame)
    except Exception as e:
        print("Inference error:", e)
        continue

    result = infer_fp16(frame)

    annotated = frame.copy()
    for box in result.boxes:
        x1, y1, x2, y2 = [int(v) for v in box.xyxy[0]]
        cv2.rectangle(annotated, (x1, y1), (x2, y2), (0,255,0), 2)
        cls = int(box.cls[0])
        label = f"{result.names[cls]} {box.conf[0]:.2f}"
        cv2.putText(annotated, label, (x1, y1-5),
                    cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0,255,0), 1)

    _, jpeg_annotated = cv2.imencode('.jpg', annotated)
    send_frame("c_detection", jpeg_annotated.tobytes())
    print("Published detections")