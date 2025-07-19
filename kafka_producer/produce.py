# kafka_producer/produce.py

from kafka import KafkaProducer
import json
import time
import random

producer = KafkaProducer(
    bootstrap_servers=["192.168.0.101:9092"],
    value_serializer=lambda v: json.dumps(v).encode("utf-8")
)


def generate_data():
    return {
        "deviceId": f"D{random.randint(1, 5)}",
        "timestamp": int(time.time()),
        "hydrationLevel": round(random.uniform(0.2, 0.8), 2),
        "bowelFrequency": random.randint(0, 2),
        "imageBase64": ""  # Simulate image
    }

if __name__ == "__main__":
    for _ in range(10):
        data = generate_data()
        print("Sending:", data)
        producer.send("sensor.health.raw", data)
        time.sleep(2)

    producer.flush()
    producer.close()
