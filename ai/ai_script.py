import os
from ultralytics import YOLO

#prepare the path to the images
script_dir = os.path.dirname(os.path.abspath(__file__))
image_path = os.path.join(script_dir, "Images", "cat_dog.jpg")


model = YOLO("yolov8m.pt")   # loads pre-trained model
results = model.predict(image_path)
result = results[0]

"""
#Shows it as the boxes
results = model.predict(
    source=image_path,     # input image
    save=True,             # save annotated image
    save_dir=os.path.join(script_dir, "runs/detect")  # optional custom output folder
)
"""

#Shows it as a 
for box in result.boxes:
    class_id = result.names[box.cls[0].item()]
    coords = [round(x) for x in box.xyxy[0].tolist()]
    conf = round(box.conf[0].item(), 2)
    print(class_id, coords, conf)
