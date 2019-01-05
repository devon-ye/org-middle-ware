package org.yutil.kafka.proxy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yutil.kafka.common.KafkaExecuteStrategy;
import org.yutil.kafka.common.MessageHeader;
import org.yutil.kafka.producer.common.KafkaProducerConfig;

import org.yutil.kafka.utils.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Devonmusa
 * @date 2017年4月2日
 */
public class KafkaSenderTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSenderTest.class);
	private KafkaProducerConfig producerConfig;
	private KafkaSenderProxy kafkaSender;
	private MessageHeader header;
	private Properties props;
	private long startTime;
	private long endTime;
	private long total;

	@Before
	public void setUp() throws FileNotFoundException, IOException, Exception {
		System.setProperty("com.sun.management.jmxremote.port", "9999");
		System.setProperty("com.sun.management.jmxremote.ssl", "false");
		System.setProperty("com.sun.management.jmxremote.authenticate", "false");
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		System.setProperty("log.home", System.getProperty("user.dir") + "/log");
		KafkaUtils.initLogback();
		producerConfig = KafkaProducerConfig.getInstance();
		producerConfig.setTopic("YDW.Q");
		//producerConfig.setTopic("DEFAULT_TOPIC.Q");
		//producerConfig.setZookeeperUrl("server1.xdpp.boco:3181");
		producerConfig.setZookeeperUrl("192.168.1.10:2182");
		//producerConfig.setZookeeperUrl("123.207.161.145:2182");
		props = new Properties();
		producerConfig.setProperties(props);
		startTime = System.nanoTime();
	}

	@Test
	public void syncSendTest() {
		kafkaSender = new KafkaSenderProxy(producerConfig, KafkaExecuteStrategy.SYNC);
		int i = 0;
		while (i < 200000) {
			header = new MessageHeader(i, 100);
			byte[] data = ("message" + i).getBytes();
			kafkaSender.send(header, data);
			LOGGER.info("header:" + header);
			total++;
			i++;
		}
	}

	@Test
	public void asyncSendTest() {
		kafkaSender = new KafkaSenderProxy(producerConfig, KafkaExecuteStrategy.ASYNC);
		int i = 0;
		while (i < 1000000) {
			header = new MessageHeader(i, 100);
		//	header = new MessageHeader(i, 6, 1);
			byte[] data = ("message" + i).getBytes();
			kafkaSender.send(header, data);
		LOGGER.info("header:" + header);
			total++;
			i++;
		}
	}

	@After
	public void tearDown() {
		
		kafkaSender.close();
		endTime = System.nanoTime();
		performancePrint(startTime,endTime);
		
	}

	private void performancePrint(long start, long end) {
		long usedTimeNS = end - start;
		long usedTimeSecond = usedTimeNS / 1000000000;
		if(usedTimeSecond ==0)
			usedTimeSecond =1;
		long avgSpeed = total / usedTimeSecond;
		LOGGER.info(" avgSpeed = " +avgSpeed +"item/s");
		LOGGER.info("usedTimeSecond=" + usedTimeSecond +"s, total="+total);
	}
}
