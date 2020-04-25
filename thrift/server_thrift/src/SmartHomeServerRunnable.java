import handler.open_closed.GateDeviceHandler;
import util.ConsoleColor;

import static util.ColouredPrinter.printlnColoured;

public class SmartHomeServerRunnable implements Runnable {

    private static final String GATE_DEVICE_IDENTIFIER = "GATE_IDENTIFIER";
    private static final String GATE_DEVICE_NAME = "GATE TO THE HOUSE";

    @Override
    public void run() {
        SmartHomeServerCreator creator = new SmartHomeServerCreator();

        GateDeviceHandler gateDeviceHandler = new GateDeviceHandler(GATE_DEVICE_IDENTIFIER, GATE_DEVICE_NAME, false);

        creator.addNewDevice(gateDeviceHandler);

        printlnColoured("Server has started running: ", ConsoleColor.GREEN_BOLD);
        creator.create();
    }
}
