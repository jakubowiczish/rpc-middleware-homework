package server;

import io.grpc.stub.StreamObserver;
import sr.grpc.generated.air_quality.AirQuality.AirQualityInfo;
import sr.grpc.generated.air_quality.AirQuality.AirQualityRequest;
import sr.grpc.generated.air_quality.AirQualityServiceGrpc.AirQualityServiceImplBase;

import java.util.Collections;

public class AirQualityService extends AirQualityServiceImplBase {

    private final DataGenerationService generationService;

    public AirQualityService(DataGenerationService generationService) {
        this.generationService = generationService;
    }

    @Override
    public void subscribeOnAirQuality(AirQualityRequest request,
                                      StreamObserver<AirQualityInfo> responseObserver) {

        final AirQualitySubscriber subscriber = AirQualitySubscriber.builder()
                .responseObserver(responseObserver)
                .errors(Collections.emptyList())
                .city(request.getCity())
                .clientId(request.getClientIdentifier())
                .build();

        generationService.subscribeOnDataGeneration(subscriber);
    }
}