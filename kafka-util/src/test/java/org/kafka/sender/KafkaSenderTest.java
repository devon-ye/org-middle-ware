package org.kafka.sender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.devonmusa.util.config.EnvironmentUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kafka.common.KafkaProducerConfig;
import org.kafka.common.MessageHeader;
import org.kafka.util.KafkaSendMode;
import org.kafka.utils.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@author  Devonmusa
*@date 2017年4月2日
*/
public class KafkaSenderTest {
	private static final Logger  logger = LoggerFactory.getLogger(KafkaSenderTest.class);
	private KafkaProducerConfig producerConfig;
	private KafkaSender kafkaSender;
	private Properties props;
	private MessageHeader header;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, Exception {
		System.setProperty("log.home", EnvironmentUtils.getAppHome()+"/log");
		KafkaUtils.initLogback();
		producerConfig = KafkaProducerConfig.getInstance();
		producerConfig.setTopic("TEST.Q");
		producerConfig.setZookeeperUrl("192.168.1.12:2181");
		props = new Properties();
		producerConfig.setProperties(props);
		kafkaSender = new KafkaSender(producerConfig,KafkaSendMode.Sync);
	}
	
	@Test
	public void sendTest(){
		int i = 0;
		while(i < 10){
			header =new MessageHeader(i, 100);
			byte[] data =("message" + i).getBytes();
			kafkaSender.send(header, data);
			logger.info("header:" + header);
			i++;
		}
	}
	
	@After
	public void tearDown(){
		kafkaSender.close();
	}
}
