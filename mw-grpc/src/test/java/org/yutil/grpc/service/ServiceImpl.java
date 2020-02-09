package org.yutil.grpc.service;

import java.util.List;

import org.yutil.grpc.proto.DemoServiceGrpc;
import org.yutil.grpc.proto.DemoServiceGrpc.DemoServiceImplBase;
import org.yutil.grpc.proto.User;
import org.yutil.grpc.proto.UserId;
import org.yutil.grpc.proto.UserList;

import io.grpc.stub.StreamObserver;

/**
 *
 * @author Devonmusa
 * @date 2017年7月29日
 */
public class ServiceImpl extends DemoServiceImplBase {
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
