package server;

import lombok.SneakyThrows;
import sr.grpc.generated.air_quality.AirQuality.AirQualityInfo;
import sr.grpc.generated.air_quality.AirQuality.AirQualityInfo.AirQualityIndex;
import sr.grpc.generated.air_quality.AirQuality.AirQualityInfo.AirQualityStatus;
import util.ConsoleColor;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toList;
import static util.ColouredPrinter.printlnColoured;

public class DataGenerationService {

    private static final List<String> LIST_OF_AVAILABLE_CITIES = List.of(
            "Chicago",
            "Los Angeles",
            "Las Vegas",
            "New York"
    );

    private static final List<String> POLLUTION_PARAMETERS = List.of(
            "PM10",
            "PM2.5",
            "CO"
    );

    private static final int NUMBER_OF_MEASUREMENTS = 3;

    private final Random random;
    private final CopyOnWriteArraySet<AirQualitySubscriber> subscribers;

    public DataGenerationService() {
        this.random = new Random();
        this.subscribers = new CopyOnWriteArraySet<>();
    }

    public void subscribeOnDataGeneration(AirQualitySubscriber subscriber) {
        subscribers.add(subscriber);
        printlnColoured("New subscriber has been added: " + subscriber, ConsoleColor.BLUE_BOLD);
    }

    @SneakyThrows
    public void generateData() {
        while (true) {
            AirQualityInfo airQualityInfo = AirQualityInfo.newBuilder()
                    .setCity(getRandomAvailableCity())
                    .setPollutionStatus(getRandomAirQualityStatus())
                    .addAllAirQualityParam(getRandomAirQualityParamsIndices())
                    .build();

            sendAirQualityInfo(airQualityInfo);
            sleep(1000);
        }
    }


    private String getRandomAvailableCity() {
        return LIST_OF_AVAILABLE_CITIES.get(random.nextInt(LIST_OF_AVAILABLE_CITIES.size()));
    }

    private AirQualityStatus getRandomAirQualityStatus() {
        final AirQualityStatus[] statuses = AirQualityStatus.values();
        return statuses[random.nextInt(statuses.length - 1)];
    }

    private List<AirQualityIndex> getRandomAirQualityParamsIndices() {
        return POLLUTION_PARAMETERS.stream()
                .map(param -> AirQualityIndex.newBuilder()
                        .setParamName(param)
                        .addAllLastMeasurements(getRandomDoubleNumbers())
                        .build())
                .collect(toList());
    }

    private List<Double> getRandomDoubleNumbers() {
        return IntStream.range(0, NUMBER_OF_MEASUREMENTS)
                .mapToObj(i -> random.nextDouble() % 100)
                .collect(toList());
    }

    private void sendAirQualityInfo(AirQualityInfo airQualityInfo) {
        for (AirQualitySubscriber subscriber : subscribers) {
            if (!Objects.equals(subscriber.getCity(), airQualityInfo.getCity())) continue;

            try {
                subscriber.getResponseObserver().onNext(airQualityInfo);
            } catch (Exception e) {
                printlnColoured("Error while sending message to subscriber: " + subscriber.getClientId(), ConsoleColor.RED_BOLD_BRIGHT);
            }
        }
    }

}