package handler;

import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import sr.rpc.thrift.FoodCoolingMode;
import sr.rpc.thrift.FridgeDevice;
import sr.rpc.thrift.InvalidOperationException;
import util.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

import static util.ColouredPrinter.printlnColoured;

public class FridgeDeviceHandler extends CoolingDeviceHandler implements FridgeDevice.Iface {

    private final AtomicReference<FoodCoolingMode> foodCoolingMode = new AtomicReference<>(FoodCoolingMode.REGULAR);

    public FridgeDeviceHandler(String identifier, String name, FoodCoolingMode foodCoolingMode) {
        super(identifier, name, 50);
        this.foodCoolingMode.set(foodCoolingMode);
    }

    @Override
    public synchronized TBaseProcessor<?> getProcessor() {
        return new FridgeDevice.Processor<>(this);
    }

    @Override
    public synchronized FoodCoolingMode getFoodCoolingMode() throws TException {
        throwIfOffAndRequested();
        return this.foodCoolingMode.get();
    }

    @Override
    public synchronized void setCoolingMode(FoodCoolingMode foodCoolingMode) throws TException {
        throwIfOffAndRequested();

        printlnColoured("Server has received a request set food cooling mode: " + foodCoolingMode.getValue(), ConsoleColor.YELLOW_BOLD);

        if (isAlreadySet(foodCoolingMode))
            throw new InvalidOperationException(1, "You cannot set such a cooling mode - it's already been set");

        printlnColoured("Request to set food cooling mode processed successfully", ConsoleColor.CYAN_BOLD);
        this.foodCoolingMode.set(foodCoolingMode);
    }

    private synchronized boolean isAlreadySet(FoodCoolingMode foodCoolingMode) {
        return this.foodCoolingMode.get() == foodCoolingMode;
    }
}
