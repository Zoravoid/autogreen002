from pi_producer import send_sensor_data
from pi_consumer import get_sensor_data

sensor_types = ["moisture", "heat", "humidity", "co2"]
current_values = dict()


def read_sensor(type):
    sensor_value = type+"_val"

    #Data types that we expect in the DB
    #Otherwise this is where the data would actually be read
    data = {
        "device_id": 1,
        sensor_value: 69.69,
        "time_stamp": "2025-11-29 14:00:00.000"
    }
    return data


def send_all():
    for sensor in sensor_types:
        data = read_sensor(sensor)
        s_type = "s_"+sensor
        send_sensor_data(s_type, data)


def recieve_all():
    for sensor in sensor_types:
        current_values = get_sensor_data(sensor)
        print(current_values)
        current_values = {}

def main():
    #This code works. Main is only for testing them. Later we need toput this in a loop

    #send_all()
    #recieve_all()
    print("Running main()")

if __name__ == "__main__":
    main()