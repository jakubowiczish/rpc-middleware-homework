package handler;

import org.apache.thrift.TException;
import sr.rpc.thrift.Device;
import sr.rpc.thrift.DevicesList;
import util.ConsoleColor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static util.ColouredPrinter.printlnColoured;

public class DevicesListController implements DevicesList.Iface {

    private List<Device> listOfDevices;

    public DevicesListController(List<HomeDeviceHandler> deviceHandlerList) {
        initializeListOfDevices(deviceHandlerList);
    }

    @Override
    public List<Device> getListOfAvailableDevices() throws TException {
        printlnColoured("Server has received a request to get a list of all available devices", ConsoleColor.YELLOW_BOLD);
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
