import handler.DevicesListController;
import handler.HomeDeviceHandler;
import lombok.SneakyThrows;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import sr.rpc.thrift.DevicesList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SmartHomeServerDevicesBuilder {

    private static final int port = 9090;
    private static final String ALL_HOME_DEVICES_SERVICE_NAME = "all";

    private final List<HomeDeviceHandler> homeDevices;
    private final TMultiplexedProcessor multiplexedProcessor;

    private TServer server;

    public SmartHomeServerDevicesBuilder() {
        homeDevices = new CopyOnWriteArrayList<>();
        multiplexedProcessor = new TMultiplexedProcessor();
    }

    public SmartHomeServerDevicesBuilder addNewDevice(HomeDeviceHandler handler) {
        homeDevices.add(handler);
        multiplexedProcessor.registerProcessor(handler.getIdentifier(), handler.getProcessor());
        return this;
    }

    @SneakyThrows
    public SmartHomeServerDevicesBuilder create() {
        multiplexedProcessor.registerProcessor(ALL_HOME_DEVICES_SERVICE_NAME, getAllDevicesProcessor());

        final TServerTransport transport = new TServerSocket(port);
        final TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();

        server =  new TThreadPoolServer(new TThreadPoolServer.Args(transport)
                .protocolFactory(protocolFactory)
                .processor(multiplexedProcessor));

        return this;
    }

    public void serve() {
        server.serve();
    }

    private DevicesList.Processor<DevicesListController> getAllDevicesProcessor() {
        return new DevicesList.Processor<>(new DevicesListController(homeDevices));
    }
}
