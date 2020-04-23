package server;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import util.ConsoleColor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static util.ColouredPrinter.printlnColoured;

public class AirQualityServer {

    private static final int port = 50051;

    private Server server;

    public void start() throws IOException {
        stop();

        DataGenerationService generationService = new DataGenerationService();
        server = createServer(generationService).start();
        new Thread(generationService::generateData).start();

        printlnColoured("Server started, listening on " + port, ConsoleColor.GREEN_BOLD);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            AirQualityServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private Server createServer(DataGenerationService generationService) {
        return NettyServerBuilder.forPort(port)
                .permitKeepAliveWithoutCalls(true)
                .permitKeepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTimeout(5, TimeUnit.SECONDS)
                .addService(new AirQualityService(generationService))
                .build();
    }
}
