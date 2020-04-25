package handler;

import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import sr.rpc.thrift.GateDevice;
import sr.rpc.thrift.InvalidOperationException;
import sr.rpc.thrift.OpenClosedMode;
import util.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

import static util.ColouredPrinter.printlnColoured;

public class GateDeviceHandler extends OpenedClosedDeviceHandler implements GateDevice.Iface {

    private final AtomicReference<Boolean> isAutomatic = new AtomicReference<>(false);

    public GateDeviceHandler(String identifier, String name, boolean isAutomatic) {
        super(identifier, name, OpenClosedMode.CLOSED);
        this.isAutomatic.set(isAutomatic);
    }

    @Override
    public TBaseProcessor<?> getProcessor() {
        return new GateDevice.Processor<>(this);
    }

    @Override
    public boolean isAutomatic() throws TException {
        return isAutomatic.get();
    }

    @Override
    public void setAutomatic() throws InvalidOperationException, TException {
        printlnColoured("Server has received a request to set gate as automatic", ConsoleColor.YELLOW_BOLD);

        if (isAlreadyAutomatic())
            throw new InvalidOperationException(1, "You cannot set gate device as automatic as it's already automatic");

        printlnColoured("Setting gate as automatic", ConsoleColor.BLUE_BOLD);
        this.isAutomatic.set(true);
    }

    @Override
    public void setNotAutomatic() throws InvalidOperationException, TException {
        printlnColoured("Server has received a request to set gate as non automatic", ConsoleColor.YELLOW_BOLD);

        if (isNotAutomaticYet())
            throw new InvalidOperationException(1, "You cannot set gate device as non automatic as it's already non automatic");

        printlnColoured("Setting gate non automatic", ConsoleColor.BLUE_BOLD);
        this.isAutomatic.set(false);
    }

    private boolean isAlreadyAutomatic() {
        return this.isAutomatic.get();
    }

    private boolean isNotAutomaticYet() {
        return !isAlreadyAutomatic();
    }
}
