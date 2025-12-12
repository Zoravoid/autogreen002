from lora_linux_rpi5 import LoRa
import time
from datetime import datetime

# Your wiring:
CS_PIN = 8
RST_PIN = 22

lora = LoRa(cs_pin=CS_PIN, rst_pin=RST_PIN)
database = []

while True:
    flag = 0
    timenow = datetime.now()
    entry = []
    output1 = []
    output2 = []
    
    time.sleep(5)
    
    print("Requesting data from Pico1...")
    lora.send(b"REQ1")
    time.sleep(2)

    bmsg = lora.receive()
    if bmsg:
        msg = bmsg.decode()
        temp_hum_output = eval(msg)
        output1 = temp_hum_output[:]
        #wtime.append(temp_hum_output)
        flag = flag + 1
        #print("Received from Pico:", output)
    
    print("Requesting data from Pico2...")
    lora.send(b"REQ2")
    time.sleep(2)
    
    bmsg2 = lora.receive()
    if bmsg2:
        msg2 = bmsg2.decode()
        moist_output = eval(msg2)
        output2.append(moist_output)
        flag = flag + 1
    
    #if flag == 2:
    entry = [timenow] + output1 + output2
    
    with open("log_file.txt","a") as f:
        f.write(str(entry) + "\n")
    database.append(entry)
    print(entry)    

    time.sleep(1)
