import handler.DevicesListHandler;
import handler.main.HomeDeviceHandler;
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

public class SmartHomeServerCreator {

    private static final int port = 9090;
    private static final String ALL_HOME_DEVICES_SERVICE_NAME = "all";

    private List<HomeDeviceHandler> homeDevices;
    private TMultiplexedProcessor multiplexedProcessor;

    public SmartHomeServerCreator() {
        homeDevices = new CopyOnWriteArrayList<>();
        multiplexedProcessor = new TMultiplexedProcessor();
    }

    public void addNewDevice(HomeDeviceHandler handler) {
        homeDevices.add(handler);
        multiplexedProcessor.registerProcessor(handler.getIdentifier(), handler.getProcessor());
    }

    @SneakyThrows
    public void create() {
        multiplexedProcessor.registerProcessor(ALL_HOME_DEVICES_SERVICE_NAME, getAllDevicesProcessor());

        final TServerTransport transport = new TServerSocket(port);
        final TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();

        final TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(transport)
                .protocolFactory(protocolFactory)
                .processor(multiplexedProcessor));

        server.serve();
    }

    private DevicesList.Processor<DevicesListHandler> getAllDevicesProcessor() {
        return new DevicesList.Processor<>(new DevicesListHandler(homeDevices));
    }
}
