package handler;

import org.apache.thrift.TException;
import sr.rpc.thrift.FoodCoolingMode;
import sr.rpc.thrift.FreezingMode;
import sr.rpc.thrift.FridgeDevice;
import sr.rpc.thrift.InvalidOperationException;

import java.util.concurrent.atomic.AtomicReference;

// TODO implement

public class FridgeDeviceHandler extends CoolingDeviceHandler implements FridgeDevice.Iface {

    private final AtomicReference<FoodCoolingMode> foodCoolingMode = new AtomicReference<>(FoodCoolingMode.OTHERS);

    public FridgeDeviceHandler(String identifier, String name, FoodCoolingMode foodCoolingMode) {
        super(identifier, name, 50);
        this.foodCoolingMode.set(foodCoolingMode);
    }

    @Override
    public FoodCoolingMode getFoodCoolingMode() throws TException {
        return null;
    }

    @Override
    public void setCoolingForMeat() throws InvalidOperationException, TException {

    }

    @Override
    public void setOtherCooling() throws InvalidOperationException, TException {

    }
}
