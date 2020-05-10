package org.mw.grpc.client;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mw.grpc.proto.User;

/**
 * @author Devonmusa
 * @date 2017年7月29日
 */
public class DemoClientTest {

	private DemoClient demoClient;

	@Before
	public void before() {
		String hosts = "127.0.0.1";
		int port = 9980;
		try {
			demoClient = new DemoClient(hosts, port);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	@Test
	public void testGetUserList() throws InterruptedException {
		System.out.println("client starting... data");
		while (true) {
			long requestId = System.currentTimeMillis();
			System.out.println("clien sending request... requestId:" + requestId);
			Iterator<User> userList = demoClient.getUserList(System.nanoTime());
			while (userList.hasNext()) {
				System.out.println("client receiving... data is" + userList.next().toString());
			}
			Thread.sleep(2000);
		}

	}

	@After
	public void after() {
		demoClient.shutDown();
	}
}
