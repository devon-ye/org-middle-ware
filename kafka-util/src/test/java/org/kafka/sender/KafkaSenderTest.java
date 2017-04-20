package org.kafka.sender;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kafka.common.KafkaProducerConfig;
import org.kafka.common.MessageHeader;
import org.kafka.util.KafkaSendMode;

/**
*@see
*@author  Devonmusa
*@date 2017年4月2日
*/
public class KafkaSenderTest {
	private KafkaProducerConfig producerConfig;
	private KafkaSender kafkaSender;
	private Properties props;
	private MessageHeader header;
	
	@Before
	public void setUp() {	
		producerConfig = KafkaProducerConfig.getInstance();
		producerConfig.setTopic("TEST.Q");
		producerConfig.setZookeeperUrl("192.168.1.8:2181");
		kafkaSender = new KafkaSender(producerConfig,KafkaSendMode.Sync);
		
	}
	
	@Test
	public void sendTest(){
		int i = 0;
		while(i < 10){
			header =new MessageHeader(i, 100);
			byte[] data =("message" + i).getBytes();
			kafkaSender.send(header, data);
			i++;
		}
	}
	
	@After
	public void tearDown(){
		kafkaSender.close();
	}
}
