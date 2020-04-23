package server;

import io.grpc.stub.StreamObserver;
import lombok.*;
import sr.grpc.generated.air_quality.AirQuality.AirQualityInfo;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AirQualitySubscriber {

    private String city;
    private String clientId;
    private List<Throwable> errors;
    private StreamObserver<AirQualityInfo> responseObserver;
}
