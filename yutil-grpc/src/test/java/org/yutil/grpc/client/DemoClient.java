package org.yutil.grpc.client;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yutil.grpc.proto.DemoServiceGrpc;
import org.yutil.grpc.proto.User;
import org.yutil.grpc.proto.UserId;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author Devonmusa
 * @date 2017年7月29日
 */
public class DemoClient {
	private Logger logger = LoggerFactory.getLogger(DemoClient.class);

	private final ManagedChannel channel;
	private final DemoServiceGrpc.DemoServiceBlockingStub blockingStub;
	private final DemoServiceGrpc.DemoServiceStub asyncStub;

	public DemoClient(String hosts, int port) {

		channel = ManagedChannelBuilder.forAddress(hosts, port)
			// Channels are secure by default (via SSL/TLS). For the example we
			// disable TLS to avoid
			// needing certificates.
			.usePlaintext(true).build();
		blockingStub = DemoServiceGrpc.newBlockingStub(channel);
		asyncStub = DemoServiceGrpc.newStub(channel);
	}

	public Iterator<User> getUserList() {
		UserId request = UserId.newBuilder().setId(1).build();
		System.out.println("request:" + request);
		Iterator<User> userList = blockingStub.findUserList(request);
		return userList;
		
	}

	public void shutDown() {
		System.out.println("channel will closed!");
		if (channel != null && !channel.isShutdown()) {
			channel.shutdown();
		}
	}
}
