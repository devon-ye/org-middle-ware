package org.yutil.grpc.proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 *service define
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: GrpcServiceInterfaceDemo.proto")
public final class DemoServiceGrpc {

  private DemoServiceGrpc() {}

  public static final String SERVICE_NAME = "DemoService.DemoService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.yutil.grpc.proto.UserId,
      org.yutil.grpc.proto.User> METHOD_GET_USER =
      io.grpc.MethodDescriptor.<org.yutil.grpc.proto.UserId, org.yutil.grpc.proto.User>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "DemoService.DemoService", "GetUser"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.yutil.grpc.proto.UserId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.yutil.grpc.proto.User.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.yutil.grpc.proto.UserId,
      org.yutil.grpc.proto.User> METHOD_FIND_USER_LIST =
      io.grpc.MethodDescriptor.<org.yutil.grpc.proto.UserId, org.yutil.grpc.proto.User>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "DemoService.DemoService", "FindUserList"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.yutil.grpc.proto.UserId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.yutil.grpc.proto.User.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DemoServiceStub newStub(io.grpc.Channel channel) {
    return new DemoServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DemoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DemoServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DemoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DemoServiceFutureStub(channel);
  }

  /**
   * <pre>
   *service define
   * </pre>
   */
  public static abstract class DemoServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getUser(org.yutil.grpc.proto.UserId request,
        io.grpc.stub.StreamObserver<org.yutil.grpc.proto.User> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_USER, responseObserver);
    }

    /**
     */
    public void findUserList(org.yutil.grpc.proto.UserId request,
        io.grpc.stub.StreamObserver<org.yutil.grpc.proto.User> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FIND_USER_LIST, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_USER,
            asyncUnaryCall(
              new MethodHandlers<
                org.yutil.grpc.proto.UserId,
                org.yutil.grpc.proto.User>(
                  this, METHODID_GET_USER)))
          .addMethod(
            METHOD_FIND_USER_LIST,
            asyncServerStreamingCall(
              new MethodHandlers<
                org.yutil.grpc.proto.UserId,
                org.yutil.grpc.proto.User>(
                  this, METHODID_FIND_USER_LIST)))
          .build();
    }
  }

  /**
   * <pre>
   *service define
   * </pre>
   */
  public static final class DemoServiceStub extends io.grpc.stub.AbstractStub<DemoServiceStub> {
    private DemoServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DemoServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoServiceStub(channel, callOptions);
    }

    /**
     */
    public void getUser(org.yutil.grpc.proto.UserId request,
        io.grpc.stub.StreamObserver<org.yutil.grpc.proto.User> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_USER, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findUserList(org.yutil.grpc.proto.UserId request,
        io.grpc.stub.StreamObserver<org.yutil.grpc.proto.User> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_FIND_USER_LIST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *service define
   * </pre>
   */
  public static final class DemoServiceBlockingStub extends io.grpc.stub.AbstractStub<DemoServiceBlockingStub> {
    private DemoServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DemoServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.yutil.grpc.proto.User getUser(org.yutil.grpc.proto.UserId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_USER, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<org.yutil.grpc.proto.User> findUserList(
        org.yutil.grpc.proto.UserId request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_FIND_USER_LIST, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *service define
   * </pre>
   */
  public static final class DemoServiceFutureStub extends io.grpc.stub.AbstractStub<DemoServiceFutureStub> {
    private DemoServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DemoServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.yutil.grpc.proto.User> getUser(
        org.yutil.grpc.proto.UserId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_USER, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER = 0;
  private static final int METHODID_FIND_USER_LIST = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DemoServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DemoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER:
          serviceImpl.getUser((org.yutil.grpc.proto.UserId) request,
              (io.grpc.stub.StreamObserver<org.yutil.grpc.proto.User>) responseObserver);
          break;
        case METHODID_FIND_USER_LIST:
          serviceImpl.findUserList((org.yutil.grpc.proto.UserId) request,
              (io.grpc.stub.StreamObserver<org.yutil.grpc.proto.User>) responseObserver);
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

  private static final class DemoServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.yutil.grpc.proto.DemoeService.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DemoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DemoServiceDescriptorSupplier())
              .addMethod(METHOD_GET_USER)
              .addMethod(METHOD_FIND_USER_LIST)
              .build();
        }
      }
    }
    return result;
  }
}
