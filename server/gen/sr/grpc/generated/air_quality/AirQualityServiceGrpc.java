package sr.grpc.generated.air_quality;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.27.0)",
    comments = "Source: air_quality.proto")
public final class AirQualityServiceGrpc {

  private AirQualityServiceGrpc() {}

  public static final String SERVICE_NAME = "AirQualityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.generated.air_quality.AirQuality.AirQualityRequest,
      sr.grpc.generated.air_quality.AirQuality.AirQualityInfo> getSubscribeOnAirQualityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeOnAirQuality",
      requestType = sr.grpc.generated.air_quality.AirQuality.AirQualityRequest.class,
      responseType = sr.grpc.generated.air_quality.AirQuality.AirQualityInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.generated.air_quality.AirQuality.AirQualityRequest,
      sr.grpc.generated.air_quality.AirQuality.AirQualityInfo> getSubscribeOnAirQualityMethod() {
    io.grpc.MethodDescriptor<sr.grpc.generated.air_quality.AirQuality.AirQualityRequest, sr.grpc.generated.air_quality.AirQuality.AirQualityInfo> getSubscribeOnAirQualityMethod;
    if ((getSubscribeOnAirQualityMethod = AirQualityServiceGrpc.getSubscribeOnAirQualityMethod) == null) {
      synchronized (AirQualityServiceGrpc.class) {
        if ((getSubscribeOnAirQualityMethod = AirQualityServiceGrpc.getSubscribeOnAirQualityMethod) == null) {
          AirQualityServiceGrpc.getSubscribeOnAirQualityMethod = getSubscribeOnAirQualityMethod =
              io.grpc.MethodDescriptor.<sr.grpc.generated.air_quality.AirQuality.AirQualityRequest, sr.grpc.generated.air_quality.AirQuality.AirQualityInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeOnAirQuality"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.generated.air_quality.AirQuality.AirQualityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.generated.air_quality.AirQuality.AirQualityInfo.getDefaultInstance()))
              .setSchemaDescriptor(new AirQualityServiceMethodDescriptorSupplier("SubscribeOnAirQuality"))
              .build();
        }
      }
    }
    return getSubscribeOnAirQualityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AirQualityServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AirQualityServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AirQualityServiceStub>() {
        @java.lang.Override
        public AirQualityServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AirQualityServiceStub(channel, callOptions);
        }
      };
    return AirQualityServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AirQualityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AirQualityServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AirQualityServiceBlockingStub>() {
        @java.lang.Override
        public AirQualityServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AirQualityServiceBlockingStub(channel, callOptions);
        }
      };
    return AirQualityServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AirQualityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AirQualityServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AirQualityServiceFutureStub>() {
        @java.lang.Override
        public AirQualityServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AirQualityServiceFutureStub(channel, callOptions);
        }
      };
    return AirQualityServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AirQualityServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void subscribeOnAirQuality(sr.grpc.generated.air_quality.AirQuality.AirQualityRequest request,
        io.grpc.stub.StreamObserver<sr.grpc.generated.air_quality.AirQuality.AirQualityInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeOnAirQualityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeOnAirQualityMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                sr.grpc.generated.air_quality.AirQuality.AirQualityRequest,
                sr.grpc.generated.air_quality.AirQuality.AirQualityInfo>(
                  this, METHODID_SUBSCRIBE_ON_AIR_QUALITY)))
          .build();
    }
  }

  /**
   */
  public static final class AirQualityServiceStub extends io.grpc.stub.AbstractAsyncStub<AirQualityServiceStub> {
    private AirQualityServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirQualityServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AirQualityServiceStub(channel, callOptions);
    }

    /**
     */
    public void subscribeOnAirQuality(sr.grpc.generated.air_quality.AirQuality.AirQualityRequest request,
        io.grpc.stub.StreamObserver<sr.grpc.generated.air_quality.AirQuality.AirQualityInfo> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeOnAirQualityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AirQualityServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AirQualityServiceBlockingStub> {
    private AirQualityServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirQualityServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AirQualityServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<sr.grpc.generated.air_quality.AirQuality.AirQualityInfo> subscribeOnAirQuality(
        sr.grpc.generated.air_quality.AirQuality.AirQualityRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeOnAirQualityMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AirQualityServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AirQualityServiceFutureStub> {
    private AirQualityServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirQualityServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AirQualityServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE_ON_AIR_QUALITY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AirQualityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AirQualityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE_ON_AIR_QUALITY:
          serviceImpl.subscribeOnAirQuality((sr.grpc.generated.air_quality.AirQuality.AirQualityRequest) request,
              (io.grpc.stub.StreamObserver<sr.grpc.generated.air_quality.AirQuality.AirQualityInfo>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AirQualityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AirQualityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.generated.air_quality.AirQuality.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AirQualityService");
    }
  }

  private static final class AirQualityServiceFileDescriptorSupplier
      extends AirQualityServiceBaseDescriptorSupplier {
    AirQualityServiceFileDescriptorSupplier() {}
  }

  private static final class AirQualityServiceMethodDescriptorSupplier
      extends AirQualityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AirQualityServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AirQualityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AirQualityServiceFileDescriptorSupplier())
              .addMethod(getSubscribeOnAirQualityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
