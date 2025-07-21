from kafka import KafkaProducer
from kafka.sasl.oauth import AbstractTokenProvider
from aws_msk_iam_sasl_signer import MSKAuthTokenProvider
import json
import socket
import time
import random

REGION = "us-east-1"
BOOTSTRAP_SERVERS = [
    "b-1-public.toilabsmskcluster.t3vzle.c17.kafka.us-east-1.amazonaws.com:9198",
    "b-2-public.toilabsmskcluster.t3vzle.c17.kafka.us-east-1.amazonaws.com:9198"
]  # IAM public; use 9098 for private VPC brokers

class MSKTokenProvider(AbstractTokenProvider):
    def __init__(self, region):
        self.region = region

    def token(self):
        token, _ = MSKAuthTokenProvider.generate_auth_token(self.region)
        return token

tp = MSKTokenProvider(REGION)

producer = KafkaProducer(
    bootstrap_servers=BOOTSTRAP_SERVERS,
    security_protocol='SASL_SSL',
    sasl_mechanism='OAUTHBEARER',
    sasl_oauth_token_provider=tp,
    client_id=socket.gethostname(),
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
        producer.flush()
        time.sleep(2)
    producer.flush()
    producer.close()