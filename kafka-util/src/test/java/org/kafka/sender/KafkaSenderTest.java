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
	private static final Logger logger = LoggerFactory.getLogger(KafkaSenderTest.class);
	private KafkaProducerConfig producerConfig;
	private KafkaSenderProxy kafkaSender;
	private Properties props;
	private MessageHeader header;

	@Before
	public void setUp() throws FileNotFoundException, IOException, Exception {
		System.setProperty("log.home", System.getProperty("user.dir")+ "/log");
		KafkaUtils.initLogback();
		producerConfig = KafkaProducerConfig.getInstance();
		producerConfig.setTopic("TEST.Q");
		producerConfig.setZookeeperUrl("192.168.1.12:2181");
		props = new Properties();
		producerConfig.setProperties(props);
	}

	long syncStartTime = System.nanoTime();

	@Test
	public void syncSendTest() {
		kafkaSender = new KafkaSenderProxy(producerConfig, KafkaExecuteMode.Sync);
		int i = 0;
		while (i < 10) {
			header = new MessageHeader(i, 100);
			byte[] data = ("message" + i).getBytes();
			kafkaSender.send(header, data);
			logger.info("header:" + header);
			i++;
		}
		performancePrint(syncEndTime, syncStartTime);
	}

	long syncEndTime = System.nanoTime();

	long asyncStartTime = System.nanoTime();

	@Test
	public void asyncSendTest() {
		kafkaSender = new KafkaSenderProxy(producerConfig, KafkaExecuteMode.Async);
		int i = 0;
		while (i < 10) {
			header = new MessageHeader(i, 100);
			byte[] data = ("message" + i).getBytes();
			kafkaSender.send(header, data);
			logger.info("header:" + header);
			i++;
		}
		performancePrint(asyncEndTime, asyncStartTime);
	}

	long asyncEndTime = System.nanoTime();

	@After
	public void tearDown() {

		kafkaSender.close();
	}

	private void performancePrint(long start, long end) {
		long usedTimeNS = end - start;
		long usedTimeMS = usedTimeNS / 1000000;
		logger.info("usedTimeNS:" + usedTimeNS + ", usedTimeMS:" + usedTimeMS);
	}
}
