from picamera2 import Picamera2
import cv2
import time
from pi_producer import send_frame

CAM0_TOPIC = "c_stream"
CAM1_TOPIC = "c_stream_2"

def start_camera_streaming():

    picam = Picamera2()
    # = cv2.videoCapture(1)

    config = picam.create_video_configuration(
        main={"size": (640, 480), "format": "RGB888"}
    )
    picam.configure(config)
    picam.start()

    print("Starting camera streams...")

    while True:
        frame = picam.capture_array()
        #ret1, frame1 = cam1.read()

        ok, jpeg = cv2.imencode(".jpg", frame)
        if ok:
            print("Cam0 OK")
            send_frame(CAM0_TOPIC, jpeg.tobytes())
        else:
            print("JPEG encode failed")

        #if ret1:
            #ok1, jpeg1 = cv2.imencode(".jpg", frame1)
            #if ok1:
                #send_frame(CAM1_TOPIC, jpeg1.tobytes())

        time.sleep(0.1)