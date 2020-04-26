package handler;

import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import sr.rpc.thrift.InvalidOperationException;
import sr.rpc.thrift.OpenClosedMode;
import sr.rpc.thrift.OpenedClosedDevice;
import util.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

import static util.ColouredPrinter.printlnColoured;

public class OpenedClosedDeviceHandler extends HomeDeviceHandler implements OpenedClosedDevice.Iface {

    private final AtomicReference<OpenClosedMode> mode = new AtomicReference<>(OpenClosedMode.CLOSED);

    public OpenedClosedDeviceHandler(String identifier, String name, OpenClosedMode mode) {
        super(identifier, name);
        this.mode.set(mode);
    }

    @Override
    public TBaseProcessor<?> getProcessor() {
        return new OpenedClosedDevice.Processor<>(this);
    }

    @Override
    public OpenClosedMode getOpenClosedMode() throws TException {
        printlnColoured("Server has received a request to get open closed mode", ConsoleColor.YELLOW_BOLD);
        return mode.get();
    }

    @Override
    public synchronized void open() throws InvalidOperationException, TException {
        printlnColoured("Server has received a request to open some device", ConsoleColor.YELLOW_BOLD);

        if (isOpened())
            throw new InvalidOperationException(1, "You cannot open the device as it's already opened");

        printlnColoured("Request to open processed successfully", ConsoleColor.CYAN_BOLD);
        this.mode.set(OpenClosedMode.OPENED);
    }

    @Override
    public synchronized void close() throws InvalidOperationException, TException {
        printlnColoured("Server has received a request to close some device", ConsoleColor.YELLOW_BOLD);

        if (isClosed())
            throw new InvalidOperationException(1, "You cannot close the device as it's already closed");

        printlnColoured("Request to close processed successfully", ConsoleColor.CYAN_BOLD);
        this.mode.set(OpenClosedMode.CLOSED);
    }

    private synchronized boolean isOpened() {
        return this.mode.get() == OpenClosedMode.OPENED;
    }

    private synchronized boolean isClosed() {
        return this.mode.get() == OpenClosedMode.CLOSED;
    }

}
