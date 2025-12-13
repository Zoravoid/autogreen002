from picamera2 import Picamera2
import cv2
import time
from pi_producer import send_frame

CAM0_TOPIC = "c_stream"
CAM1_TOPIC = "c_stream_2"

def start_camera_streaming():

    picam = Picamera2(camera_num= 0)
    picam2 = Picamera2(camera_num= 1)

    config = picam.create_video_configuration(
        main={"size": (640, 480), "format": "RGB888"}
    )
    picam.configure(config)
    picam.start()

    config2 = picam2.create_video_configuration(
        main={"size": (640, 480), "format": "RGB888"}
    )
    picam2.configure(config2)
    picam2.start()

    print("Starting camera streams...")

    while True:
        frame = picam.capture_array()
        frame2 = picam2.capture_array()

        ok, jpeg = cv2.imencode(".jpg", frame)
        if ok:
            print("Cam0 OK")
            send_frame(CAM0_TOPIC, jpeg.tobytes())
        else:
            print("JPEG encode failed")

        ok2, jepg = cv2.imencode(".jpg", frame2)
        if ok2:
            print("Cam1 OK")
            send_frame(CAM1_TOPIC, jepg.tobytes())
        else:
            print("JEPG encode failed")

        time.sleep(0.1)