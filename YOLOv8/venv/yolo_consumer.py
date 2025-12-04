from kafka import KafkaConsumer, KafkaProducer
from ultralytics import YOLO
import cv2
import numpy as np
import json

model = YOLO('yolov8n.pt')

frame_consumer = KafkaConsumer(
    'c_stream', 'c_stream_2',
    bootstrap_servers=['lilithvoid.local:9092'],
    auto_offset_reset='latest',
    enable_auto_commit=True,
    group_id='yolo-ai'
)

result_producer = KafkaProducer(
    bootstrap_servers=['lilithvoid.local:9092'],
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

print ("YOLOv8 Consumer is running...")

for msg in frame_consumer:
    topic = msg.topic
    jpeg_bytes = msg.value
    img_array = np.frombuffer(jpeg_bytes, dtype=np.uint8)
    frame = cv2.imdecode(img_array, cv2.IMREAD_COLOR)
    results = model(frame)[0]
    detections = []
    for box in results.boxes:
        cls = int(box.cls[0])
        conf = float(box.conf[0])
        xyxy = box.xyxy[0].tolist()
        detections.append({
            'camera': topic,
            'class': model.names[cls],
            'confidence': conf,
            'bbox': xyxy
        })
    
    result_producer.send("c_detection", detections)
    print("Published detections:", detections)