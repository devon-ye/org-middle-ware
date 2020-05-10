package org.mw.grpc.service;

import org.mw.grpc.proto.DemoServiceGrpc;
import org.mw.grpc.proto.User;
import org.mw.grpc.proto.UserId;
import org.mw.grpc.proto.UserList;

import io.grpc.stub.StreamObserver;

/**
 * @author Devonmusa
 * @date 2017年7月29日
 */
public class ServiceImpl extends DemoServiceGrpc.DemoServiceImplBase {
	private UserList users;

	@Override
	public void getUser(UserId request, StreamObserver<User> responseObserver) {
		System.out.println("###############you can call server################");
		long id = request.getId();
		if (id == 1L) {

		}
		responseObserver.onCompleted();

	}

	@Override
	public void findUserList(UserId request, StreamObserver<User> responseObserver) {
		long requestId = request.getId();
		System.out.println("server processing... request,requestId:" + requestId);

		if (requestId % 3 == 0) {
			//responseObserver.onError(new RuntimeException("service call fail! id:" + requestId));
		} else if (requestId % 5 == 0) {
			responseObserver.onCompleted();
			return;
		} else {
			User user =checkFeature(request);
			responseObserver.onNext(user);
			System.out.println("server responsed... data:" + user);

			return;
		}
		responseObserver.onCompleted();
	}

	private User checkFeature(UserId userId) {

		UserList.Builder userList = users.newBuilder();
		if (userId.getId() % 2 == 0) {
			int i = 0;
			while (i < 2) {
				User.Builder user = User.newBuilder();
				user.setId(userId.getId());
				user.setName("TEST_USER_" + userId.getId() + "_" + i);
				userList.setUsers(user).build();

				i++;
			}
		} else {
			User.Builder user = User.newBuilder();
			user.setId(userId.getId());
			user.setName("TEST_USER_" + userId.getId() + "_#");
			userList.setUsers(user);
		}
		while (userList.hasUsers()) {
			System.out.println(" ################# userList=" + userList);
			return userList.getUsers();
		}

		return null;
	}

}
