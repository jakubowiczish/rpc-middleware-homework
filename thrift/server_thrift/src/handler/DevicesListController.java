package handler;

import org.apache.thrift.TException;
import sr.rpc.thrift.Device;
import sr.rpc.thrift.DevicesList;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DevicesListController implements DevicesList.Iface {

    private List<Device> listOfDevices;

    public DevicesListController(List<HomeDeviceHandler> deviceHandlerList) {
        initializeListOfDevices(deviceHandlerList);
    }

    @Override
    public List<Device> getListOfAvailableDevices() throws TException {
        return listOfDevices;
    }

    private void initializeListOfDevices(List<HomeDeviceHandler> deviceHandlerList) {
        listOfDevices = deviceHandlerList.stream()
                .map(this::createFrom)
                .collect(toList());
    }

    private Device createFrom(HomeDeviceHandler homeDeviceHandler) {
        return new Device(homeDeviceHandler.getName(), homeDeviceHandler.getIdentifier());
    }
}