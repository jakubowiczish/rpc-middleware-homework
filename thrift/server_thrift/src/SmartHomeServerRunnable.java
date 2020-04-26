import handler.FreezerDeviceHandler;
import handler.FridgeDeviceHandler;
import handler.GateDeviceHandler;
import sr.rpc.thrift.FoodCoolingMode;
import sr.rpc.thrift.FreezingMode;
import util.ConsoleColor;

import static util.ColouredPrinter.printlnColoured;

public class SmartHomeServerRunnable implements Runnable {

    @Override
    public void run() {
        SmartHomeServerDevicesBuilder creator = new SmartHomeServerDevicesBuilder()
                .addNewDevice(createGateDeviceHandler())
                .addNewDevice(createFreezerDeviceHandler())
                .addNewDevice(createFridgeDeviceHandler())
                .create();

        printlnColoured("Server has started running: ", ConsoleColor.GREEN_BOLD);
        creator.serve();
    }

    private GateDeviceHandler createGateDeviceHandler() {
        return new GateDeviceHandler("GATE_IDENTIFIER", "GATE TO THE HOUSE", false);
    }

    private FreezerDeviceHandler createFreezerDeviceHandler() {
        return new FreezerDeviceHandler("FREEZER_IDENTIFIER", "FREEZER", FreezingMode.LOW);
    }

    private FridgeDeviceHandler createFridgeDeviceHandler() {
        return new FridgeDeviceHandler("FRIDGE_IDENTIFIER", "FRIDGE", FoodCoolingMode.REGULAR);
    }
}
