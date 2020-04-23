package server;

public class AirQualityServerRunner {

    public static void main(String[] args) throws InterruptedException {
        final AirQualityServer server = new AirQualityServer();
        try {
            server.start();
        } catch (Exception ignored) {
        }

        server.blockUntilShutdown();
    }
}
