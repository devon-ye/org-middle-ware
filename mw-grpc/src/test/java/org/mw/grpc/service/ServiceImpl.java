package org.mw.grpc.service;

import org.mw.grpc.proto.DemoServiceGrpc;
import org.mw.grpc.proto.User;
import org.mw.grpc.proto.UserId;
import org.mw.grpc.proto.UserList;

import io.grpc.stub.StreamObserver;

/**
 *
 * @author Devonmusa
 * @date 2017年7月29日
 */
public class ServiceImpl extends DemoServiceGrpc.DemoServiceImplBase {
	private UserList users;

	@Override
	public void getUser(UserId request, StreamObserver<User> responseObserver) {
		long id = request.getId();
		if (id == 1L) {

		}
		responseObserver.onCompleted();

	}

	@Override
	public void findUserList(UserId request, StreamObserver<User> responseObserver) {
		responseObserver.onNext(checkFeature(request));
		System.out.println("###############you can call server################");
		responseObserver.onCompleted();
	}

	private User checkFeature(UserId userId) {
		System.out.println("###################### userId = " + userId);
		if (userId.getId() == 1) {
			UserList.Builder userList = users.newBuilder();
			
			User.Builder user = User.newBuilder();
			user.setId(9999);
			user.setName("TEST_USER");
			userList.setUsers(user);
			while (userList.hasUsers()) {
				System.out.println(" ################# userList=" + userList);
				return userList.getUsers();
			}
		}
		return null;
	}

}
