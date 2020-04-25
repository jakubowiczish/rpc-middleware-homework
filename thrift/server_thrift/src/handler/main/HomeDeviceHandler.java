package handler.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.thrift.TBaseProcessor;
import sr.rpc.thrift.HomeDevice;

@Getter
@AllArgsConstructor
public abstract class HomeDeviceHandler implements HomeDevice.Iface {

    private final String identifier;
    private final String name;

    public abstract TBaseProcessor<?> getProcessor();
}
