package handler;

import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import sr.rpc.thrift.InvalidOperationException;
import sr.rpc.thrift.SwitchMode;
import sr.rpc.thrift.SwitchableDevice;
import util.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

import static util.ColouredPrinter.printlnColoured;

public class SwitchableDeviceHandler extends HomeDeviceHandler implements SwitchableDevice.Iface {

    private final AtomicReference<SwitchMode> mode = new AtomicReference<>(SwitchMode.OFF);

    public SwitchableDeviceHandler(String identifier, String name, SwitchMode mode) {
        super(identifier, name);
        this.mode.set(mode);
    }

    @Override
    public synchronized TBaseProcessor<?> getProcessor() {
        return new SwitchableDevice.Processor<>(this);
    }

    @Override
    public synchronized SwitchMode getCurrentMode() throws TException {
        return this.mode.get();
    }

    @Override
    public synchronized void turnOn() throws InvalidOperationException, TException {
        printlnColoured("Server has received a request to turn on some device", ConsoleColor.YELLOW_BOLD);

        if (isOn())
            throw new InvalidOperationException(1, "You cannot turn on the device as it's already on");

        printlnColoured("Request to turn on processed successfully", ConsoleColor.CYAN_BOLD);
        this.mode.set(SwitchMode.ON);
    }

    @Override
    public synchronized void turnOff() throws InvalidOperationException, TException {
        printlnColoured("Server has received a request to turn off some device", ConsoleColor.YELLOW_BOLD);

        if (isOff())
            throw new InvalidOperationException(1, "You cannot turn off the device as it's already off");

        printlnColoured("Request to turn off processed successfully", ConsoleColor.CYAN_BOLD);
        this.mode.set(SwitchMode.OFF);
    }

    private synchronized boolean isOn() {
        return this.mode.get() == SwitchMode.ON;
    }

    private synchronized boolean isOff() {
        return this.mode.get() == SwitchMode.OFF;
    }

}
