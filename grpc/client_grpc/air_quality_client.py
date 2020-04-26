import threading
import time
import uuid

import grpc

from gen import air_quality_pb2
from gen import air_quality_pb2_grpc


def run():
    city_name = input("Choose name of the city that you want to subscribe for air quality info: ")
    print(city_name)

    while True:
        try:
            channel = create_channel()
            print("Client's connecting...")

            air_quality_service_stub = air_quality_pb2_grpc.AirQualityServiceStub(channel)
            client_identifier = str(uuid.uuid1())
            request = create_request(city_name, client_identifier)

            future_result = air_quality_service_stub.SubscribeOnAirQuality(request)

            for item in future_result:
                print(item)

            print('Server has ended processing')

        except:
            print("Connection issues...")
            time.sleep(1)


def create_channel():
    return grpc.insecure_channel(
        'localhost:50051',
        options=[
            ('grpc.keepalive_time_ms', 10000),
            ('grpc.keepalive_timeout_ms', 5000),
            ('grpc.keepalive_permit_without_calls', True)
        ]
    )


def create_request(city_name, client_identifier):
    return air_quality_pb2.AirQualityRequest(
        city=city_name,
        clientIdentifier=client_identifier)


def get_air_quality_info():
    threading.Thread(
        target=run,
        args="", daemon=True).start()


get_air_quality_info()

while True:
    pass
