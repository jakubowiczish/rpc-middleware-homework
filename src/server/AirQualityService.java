package server;

import io.grpc.stub.StreamObserver;
import sr.grpc.generated.air_quality.AirQuality.AirQualityInfo;
import sr.grpc.generated.air_quality.AirQuality.AirQualityRequest;
import sr.grpc.generated.air_quality.AirQualityServiceGrpc;

public class AirQualityService extends AirQualityServiceGrpc.AirQualityServiceImplBase {


    @Override
    public void subscribeOnAirQuality(AirQualityRequest request,
                                      StreamObserver<AirQualityInfo> responseObserver) {
        super.subscribeOnAirQuality(request, responseObserver);
    }
}
