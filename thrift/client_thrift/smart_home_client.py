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


transport = create_buffered_transport()
transport.open()
transport_protocol = create_transport_protocol(transport)

gate = GateDevice.Client(get_device_protocol(transport_protocol, "GATE_IDENTIFIER"))
freezer = FreezerDevice.Client(get_device_protocol(transport_protocol, "FREEZER_IDENTIFIER"))
fridge = FridgeDevice.Client(get_device_protocol(transport_protocol, "FRIDGE_IDENTIFIER"))


def get_list_of_all_available_devices(transport_protocol_arg):
    return DevicesList.Client(
        get_device_protocol(transport_protocol_arg, "all")
    ).getListOfAvailableDevices()


def print_all_available_devices():
    all_available_devices = get_list_of_all_available_devices(transport_protocol)
    for x in all_available_devices:
        print(x)
    return True


def open_gate():
    gate.open()
    print("Gate is now opened")
    return True


def close_gate():
    gate.close()
    print("Gate is now closed")
    return True


def get_gate_open_closed_mode():
    print(OpenClosedMode._VALUES_TO_NAMES.get(gate.getOpenClosedMode()))
    return True


def set_gate_auto():
    gate.setAutomatic()
    print("Gate is now automatic")
    return True


def set_gate_not_auto():
    gate.setNotAutomatic()
    print("Gate is now not automatic")
    return True


def get_gate_auto_status():
    print(gate.isAutomatic())
    return True


def turn_on_freezer():
    freezer.turnOn()
    print("Freezer is now on")
    return True


def turn_off_freezer():
    freezer.turnOff()
    print("Freezer is now off")
    return True


def get_freezer_on_off_status():
    print(SwitchMode._VALUES_TO_NAMES.get(freezer.getOnOffStatus()))
    return True


def get_freezer_power():
    print(freezer.getPower())
    return True


def set_freezer_power():
    power = int(input("Choose power level (from 0 to 100) "))
    if power < 0 or power > 100:
        raise InvalidArgumentsException("Power must be a value between 0 and 100")
    freezer.setPower(power)
    return True


def lower_freezing():
    freezer.lowerFreezing()
    print("Freezing is now lower")
    return True


def increase_freezing():
    freezer.increaseFreezing()
    print("Freezing is now higher")
    return True


def turn_on_fridge():
    fridge.turnOn()
    print("Fridge is now on")
    return True


def turn_off_fridge():
    fridge.turnOff()
    print("Fridge is now off")
    return True


def get_cooling_mode():
    print(FoodCoolingMode._VALUES_TO_NAMES.get(fridge.getFoodCoolingMode()))
    return True


def set_cooling_mode():
    print(FoodCoolingMode._NAMES_TO_VALUES)
    cooling_mode = int(input("Choose number of cooling mode from those above:"))
    if cooling_mode == 1:
        fridge.setCoolingMode(FoodCoolingMode.REGULAR)
        print("Cooling mode is now regular")
    elif cooling_mode == 2:
        fridge.setCoolingMode(FoodCoolingMode.ENERGY_SAVING)
        print("Cooling mode is now energy saving")
    elif cooling_mode == 3:
        fridge.setCoolingMode(FoodCoolingMode.MAX)
        print("Cooling mode is now maximum")

    return True


def get_fridge_on_off_status():
    print(SwitchMode._VALUES_TO_NAMES.get(fridge.getOnOffStatus()))
    return True


def print_help():
    for x in commands:
        print("> " + x[0])
    return True


commands = [
    ("help", print_help),
    ("list", print_all_available_devices),
    ("open gate", open_gate),
    ("close gate", close_gate),
    ("set gate auto", set_gate_auto),
    ("get gate open closed mode", get_gate_open_closed_mode),
    ("set gate not auto", set_gate_not_auto),
    ("get gate auto status", get_gate_auto_status),
    ("turn on freezer", turn_on_freezer),
    ("turn off freezer", turn_off_freezer),
    ("set freezer power", set_freezer_power),
    ("get freezer power", get_freezer_power),
    ("get freezer on off status", get_freezer_on_off_status),
    ("lower freezing", lower_freezing),
    ("increase freezing", increase_freezing),
    ("turn on fridge", turn_on_fridge),
    ("turn off fridge", turn_off_fridge),
    ("set cooling mode", set_cooling_mode),
    ("get cooling mode", get_cooling_mode),
    ("get fridge on off status", get_fridge_on_off_status),
]


def execute_given_command(command):
    for x in commands:
        if command == str(x[0]):
            return x[1]()
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
