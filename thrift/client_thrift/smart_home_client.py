import sys

sys.path.append("gen-py")

from smart_home import *

from thrift.transport import *
from thrift.protocol import TMultiplexedProtocol
from thrift.protocol import TBinaryProtocol


def create_buffered_transport():
    return TTransport.TBufferedTransport(TSocket.TSocket("localhost", 9090))


def create_transport_protocol(opened_transport):
    return TBinaryProtocol.TBinaryProtocol(opened_transport)


def get_device_protocol_for_identifier(transport_protocol_arg, identifier):
    return TMultiplexedProtocol.TMultiplexedProtocol(transport_protocol_arg, identifier)


def get_list_of_all_available_devices(transport_protocol_arg):
    return DevicesList.Client(
        get_device_protocol_for_identifier(transport_protocol_arg, "all")
    ).getListOfAvailableDevices()


if __name__ == "__main__":
    transport = create_buffered_transport()
    transport.open()

    transport_protocol = create_transport_protocol(transport)

    all_available_devices = get_list_of_all_available_devices(transport_protocol)
    for x in all_available_devices:
        print(x)
