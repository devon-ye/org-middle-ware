package org.kafka.sender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kafka.common.KafkaExecuteMode;
import org.kafka.common.MessageHeader;
import org.kafka.producer.common.KafkaProducerConfig;
import org.kafka.proxy.KafkaSenderProxy;

import org.kafka.utils.KafkaUtils;
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
		System.setProperty("log.home", System.getProperty("user.dir") + "/log");
		KafkaUtils.initLogback();
		producerConfig = KafkaProducerConfig.getInstance();
		producerConfig.setTopic("TEST-M.Q");
		producerConfig.setZookeeperUrl("192.168.1.12:2181");
		props = new Properties();
		producerConfig.setProperties(props);
		startTime = System.nanoTime();
	}

	@Test
	public void syncSendTest() {
		kafkaSender = new KafkaSenderProxy(producerConfig, KafkaExecuteMode.Sync);
		int i = 0;
		while (i < 100000) {
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
		kafkaSender = new KafkaSenderProxy(producerConfig, KafkaExecuteMode.Async);
		int i = 0;
		while (i < 100000) {
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
		LOGGER.info("usedTimeNS=" + usedTimeNS + "ns, usedTimeSecond=" + usedTimeSecond +"s, total="+total+", avgSpeed=" +avgSpeed +"item/s");
	}
}
