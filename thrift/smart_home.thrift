namespace java sr.rpc.thrift

exception InvalidOperationException {
  1: i32 whatOp,
  2: string why
}

exception InvalidArgumentsException {
  1: i32 argNo,
  2: string reason
}

enum SwitchMode {
    ON = 1,
    OFF = 2
}

enum OpenClosedMode {
    OPENED = 1,
    CLOSED = 2
}

enum FreezingMode {
    HIGH = 1,
    LOW = 2
}

enum FoodCoolingMode {
    MEAT = 1,
    OTHERS = 2
}

service HomeDevice {
    string getName(),
    string getIdentifier()
}

service OpenedClosedDevice extends HomeDevice {
    OpenClosedMode getOpenClosedMode(),
    void open() throws (1: InvalidOperationException e),
    void close() throws (1: InvalidOperationException e)
}

service GateDevice extends OpenedClosedDevice {
    bool isAutomatic(),
    void setAutomatic() throws (1: InvalidOperationException e),
    void setNotAutomatic() throws (1: InvalidOperationException e)
}

service SwitchableDevice extends HomeDevice {
    SwitchMode getOnOffStatus(),
    void turnOn() throws (1: InvalidOperationException e),
    void turnOff() throws (1: InvalidOperationException e)
}

service CoolingDevice extends SwitchableDevice {
    i32 getPower(),
    void setPower(1: i32 power) throws (1: InvalidArgumentsException e)
}

service FridgeDevice extends CoolingDevice {
    FoodCoolingMode getFoodCoolingMode(),
    void setCoolingForMeat() throws (1: InvalidOperationException e),
    void setOtherCooling() throws (1: InvalidOperationException e)
}

service FreezerDevice extends CoolingDevice {
    FreezingMode getFreezingMode(),
    void lowerFreezing() throws (1: InvalidOperationException e)
    void increaseFreezing() throws (1: InvalidOperationException e)
}

struct Device {
    1: string name,
    2: string identifier
}

service DevicesList {
    list<Device> getListOfAvailableDevices()
}
