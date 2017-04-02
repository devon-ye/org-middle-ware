package org.kafka.sender;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kafka.async.common.MessageHeader;

/**
*@see
*@author  Devonmusa
*@date 2017年4月2日
*/
public class KafkaSenderTest {
	private KafkaSender kafkaSender;
	private Properties props;
	private MessageHeader header;
	
	@Before
	public void setUp() {
		kafkaSender = new KafkaSender(props,KafkaSendMode.Sync);
	}
	
	@Test
	public void sendTest(){
		byte[] data ="ss".getBytes();
		kafkaSender.send(header, data);
	}
	
	@After
	public void tearDown(){
		kafkaSender.close();
	}
}
