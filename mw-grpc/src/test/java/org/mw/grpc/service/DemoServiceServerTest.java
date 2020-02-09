package org.mw.grpc.service;

import java.io.IOException;

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
			try {
				Thread.sleep(1000 * 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		test();
	}
}
