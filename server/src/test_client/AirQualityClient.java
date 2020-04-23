package test_client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.SneakyThrows;
import sr.grpc.generated.air_quality.AirQuality.AirQualityInfo;
import sr.grpc.generated.air_quality.AirQuality.AirQualityRequest;
import sr.grpc.generated.air_quality.AirQualityServiceGrpc;
import sr.grpc.generated.air_quality.AirQualityServiceGrpc.AirQualityServiceBlockingStub;
import util.ConsoleColor;

import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import static java.lang.Thread.sleep;
import static util.ColouredPrinter.printlnColoured;

public class AirQualityClient {

    private final ManagedChannel channel;
    private final AirQualityServiceBlockingStub blockingStub;

    public AirQualityClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        printlnColoured("Client started", ConsoleColor.GREEN_BOLD);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            printlnColoured("Shutting down client", ConsoleColor.RED_BOLD);
            channel.shutdown();
        }));

        blockingStub = AirQualityServiceGrpc.newBlockingStub(channel);
    }

    public void start() {
        subscribeOnAirQualityInfo();
        channel.shutdown();
    }

    @SneakyThrows
    private void subscribeOnAirQualityInfo() {
        final String cityName = chooseCityToGetInfoAbout();

        final AirQualityRequest request = AirQualityRequest
                .newBuilder()
                .setCity(cityName)
                .setClientIdentifier(UUID.randomUUID().toString())
                .build();

        Iterator<AirQualityInfo> airQualityInfoIterator;
        while (true) {
            try {
                airQualityInfoIterator = blockingStub.subscribeOnAirQuality(request);

                while (airQualityInfoIterator.hasNext()) {
                    AirQualityInfo response = airQualityInfoIterator.next();
                    printlnColoured(response.toString(), ConsoleColor.YELLOW_BOLD);
                }

            } catch (Exception e) {
                printlnColoured("Connection issues....", ConsoleColor.RED_BOLD_BRIGHT);
                sleep(1000);
            }
        }
    }

    private String chooseCityToGetInfoAbout() {
        printlnColoured("Choose name of the city that you want to subscribe for air quality info: ", ConsoleColor.CYAN_BOLD);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
