package client;

public class AirQualityClientRunner {

    private static final String host = "localhost";
    private static final int port = 50051;

    public static void main(String[] args) {
        AirQualityClient client = new AirQualityClient(host, port);
        client.start();
    }
}
