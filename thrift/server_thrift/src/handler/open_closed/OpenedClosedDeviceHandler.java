package handler.open_closed;

import handler.main.HomeDeviceHandler;
import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import sr.rpc.thrift.InvalidOperationException;
import sr.rpc.thrift.OpenClosedMode;
import sr.rpc.thrift.OpenedClosedDevice;

import java.util.concurrent.atomic.AtomicReference;

public class OpenedClosedDeviceHandler extends HomeDeviceHandler implements OpenedClosedDevice.Iface {

    private AtomicReference<OpenClosedMode> mode = new AtomicReference<>(OpenClosedMode.CLOSED);

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
        return mode.get();
    }

    @Override
    public void open() throws InvalidOperationException, TException {
        if (isOpened())
            throw new InvalidOperationException(1, "You cannot open the device as it's already opened");

        this.mode.set(OpenClosedMode.OPENED);
    }

    @Override
    public void close() throws InvalidOperationException, TException {
        if (isClosed())
            throw new InvalidOperationException(1, "You cannot close the device as it's already closed");

        this.mode.set(OpenClosedMode.CLOSED);
    }

    private synchronized boolean isOpened() {
        return this.mode.get() == OpenClosedMode.OPENED;
    }

    private synchronized boolean isClosed() {
        return this.mode.get() == OpenClosedMode.CLOSED;
    }


}
