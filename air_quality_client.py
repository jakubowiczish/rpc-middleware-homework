import random
import threading
import time

import grpc

from gen import weather_pb2
from gen import weather_pb2_grpc

def run():
    city_name = input("Choose name of the city that you want to subscribe for air quality info:")
    print(city_name)