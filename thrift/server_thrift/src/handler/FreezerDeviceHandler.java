package handler;

import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import sr.rpc.thrift.FreezerDevice;
import sr.rpc.thrift.FreezingMode;
import sr.rpc.thrift.InvalidOperationException;
import util.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

import static util.ColouredPrinter.printlnColoured;

public class FreezerDeviceHandler extends CoolingDeviceHandler implements FreezerDevice.Iface {

    private final AtomicReference<FreezingMode> freezingMode = new AtomicReference<>(FreezingMode.LOW);

    public FreezerDeviceHandler(String identifier, String name, FreezingMode freezingMode) {
        super(identifier, name, 50);
        this.freezingMode.set(freezingMode);
    }

    @Override
    public synchronized TBaseProcessor<?> getProcessor() {
        return new FreezerDevice.Processor<>(this);
    }

    @Override
    public synchronized FreezingMode getFreezingMode() throws TException {
        throwIfOffAndRequested();
        return this.freezingMode.get();
    }

    @Override
    public synchronized void increaseFreezing() throws InvalidOperationException, TException {
        throwIfOffAndRequested();

        printlnColoured("Server has received a request to increase the freezing", ConsoleColor.YELLOW_BOLD);

        if (isHigh())
            throw new InvalidOperationException(1, "You cannot increase the freezing as it's already high");

        printlnColoured("Request to increase freezing processed successfully", ConsoleColor.CYAN_BOLD);
        this.freezingMode.set(FreezingMode.HIGH);
    }

    @Override
    public synchronized void lowerFreezing() throws InvalidOperationException, TException {
        throwIfOffAndRequested();

        printlnColoured("Server has received a request to lower the freezing", ConsoleColor.YELLOW_BOLD);

        if (isLow())
            throw new InvalidOperationException(1, "You cannot lower the freezing as it's already low");

        printlnColoured("Request to lower freezing processed successfully", ConsoleColor.CYAN_BOLD);
        this.freezingMode.set(FreezingMode.LOW);
    }

    private synchronized boolean isHigh() {
        return this.freezingMode.get() == FreezingMode.HIGH;
    }

    private synchronized boolean isLow() {
        return this.freezingMode.get() == FreezingMode.LOW;
    }

}
