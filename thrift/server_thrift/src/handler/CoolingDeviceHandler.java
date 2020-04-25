package handler;

import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import sr.rpc.thrift.CoolingDevice;
import sr.rpc.thrift.InvalidArgumentsException;
import sr.rpc.thrift.InvalidOperationException;
import sr.rpc.thrift.SwitchMode;
import util.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

import static util.ColouredPrinter.printlnColoured;

public class CoolingDeviceHandler extends SwitchableDeviceHandler implements CoolingDevice.Iface {

    private final AtomicReference<Integer> power = new AtomicReference<>(0);

    public CoolingDeviceHandler(String identifier, String name, int power) {
        super(identifier, name, SwitchMode.OFF);
        this.power.set(power);
    }

    @Override
    public synchronized TBaseProcessor<?> getProcessor() {
        return new CoolingDevice.Processor<>(this);
    }

    @Override
    public synchronized int getPower() throws TException {
        return this.power.get();
    }

    @Override
    public synchronized void setPower(int power) throws InvalidArgumentsException, TException {
        printlnColoured("Server has received a request to set the power level of the device", ConsoleColor.YELLOW_BOLD);

        if (isPowerValueIncorrect(power))
            throw new InvalidOperationException(1, "You cannot set power value as: " + power + ", only values between 0 and 100 allowed");

        printlnColoured("Request to turn on processed successfully", ConsoleColor.CYAN_BOLD);
        this.power.set(power);
    }

    private synchronized boolean isPowerValueIncorrect(int power) {
        return !isPowerValueCorrect(power);
    }

    private synchronized boolean isPowerValueCorrect(int power) {
        return power >= 0 && power <= 100;
    }

}
