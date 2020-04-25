import sys

sys.path.append("gen-py")

from smart_home import *
from smart_home.ttypes import *

from thrift.transport import *
from thrift.protocol import TMultiplexedProtocol
from thrift.protocol import TBinaryProtocol


def create_buffered_transport():
    return TTransport.TBufferedTransport(TSocket.TSocket("localhost", 9090))


def create_transport_protocol(opened_transport):
    return TBinaryProtocol.TBinaryProtocol(opened_transport)


def get_device_protocol(transport_protocol_arg, identifier):
    return TMultiplexedProtocol.TMultiplexedProtocol(transport_protocol_arg, identifier)


def get_list_of_all_available_devices(transport_protocol_arg):
    return DevicesList.Client(
        get_device_protocol(transport_protocol_arg, "all")
    ).getListOfAvailableDevices()


def print_all_available_devices():
    all_available_devices = get_list_of_all_available_devices(transport_protocol)
    for x in all_available_devices:
        print(x)


transport = create_buffered_transport()
transport.open()
transport_protocol = create_transport_protocol(transport)

gate = GateDevice.Client(get_device_protocol(transport_protocol, "GATE_IDENTIFIER"))
freezer = FrezerDevice.Client(get_device_protocol(transport_protocol, "FREEZER_IDENTIFIER"))


def execute_given_command(command):
    if command == "list":
        print_all_available_devices()
        return True
    elif command == "open gate":
        gate.open()
        return True
    elif command == "close gate":
        gate.close()
        return True
    elif command == "get gate mode":
        print(gate.getOpenClosedMode())
        return True
    elif command == "set gate auto":
        gate.setAutomatic()
        return True
    elif command == "set gate non auto":
        gate.setNotAutomatic()
        return True
    elif command == "set freezer power":
        power = input("Choose power level (from 0 to 100)")
        freezer.setPower(power)
        return True
    elif command == "lower freezing":
        freezer.lowerFreezing()
        return True
    elif command == "increase freezing":
        freezer.increaseFreezing()
        return True
    return False


if __name__ == "__main__":
    while True:
        command_to_execute = input("Choose what to do -> ")
        try:
            if execute_given_command(command_to_execute):
                print("Command executed successfully!")
            else:
                print("Unknown command: " + command_to_execute)
        except InvalidOperationException as e:
            print(f'Invalid operation: {e.why}')
        except InvalidArgumentsException as e:
            print(f'Invalid argument: {e.args}')
        except:
            print("Unknown exception occurred")
