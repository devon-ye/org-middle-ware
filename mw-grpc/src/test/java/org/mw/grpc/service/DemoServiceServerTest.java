package org.mw.grpc.service;


import org.junit.Test;

/**
*
*@author Devonmusa
*@date   2017年7月29日
*/
public class DemoServiceServerTest {
	
	@Test
	public static void test() {
		DemoServer server = new DemoServer();
		try {
			server.start();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		test();
	}
}
