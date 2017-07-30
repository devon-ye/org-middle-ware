package org.kafka.producer.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;

import org.apache.kafka.common.PartitionInfo;

import org.kafka.common.MessageHeader;
import org.kafka.producer.common.KafkaProducerConfig;
import org.kafka.producer.common.ProducerRecordWrapper;
import org.kafka.producer.common.SendDataThread;
import org.kafka.proxy.AbstractSenderStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaAsyncProducer extends AbstractSenderStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncProducer.class);

	private volatile LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue;

	private ProducerRecordWrapper producerRecordWrapper;
	private static CountDownLatch countDownLatch;
	private SendDataThread sendDataThread;
	private List<PartitionInfo> partitionInfos;
	private KafkaAsyncSendWrapper asyncSendWrapper;
	private String topic;

	private final List<SendDataThread> sendDataThreads = new ArrayList<>();
	public static final Map<Integer, LinkedTransferQueue<ProducerRecordWrapper>> PARTITION_SENDQUEU_MAP = new ConcurrentHashMap<>();

	public KafkaAsyncProducer(KafkaProducerConfig producerConfig) {
		if (producerConfig == null) {
			LOG.error("KafkaAsyncProducer instaned fail, Exception:" + producerConfig);
			return;
		}

		try {
			asyncSendWrapper = new KafkaAsyncSendWrapper(producerConfig);
			partitionInfos = asyncSendWrapper.getPartitionInfos();
			topic = producerConfig.getTopic();
			producerRecordWrapper = new ProducerRecordWrapper(topic, partitionInfos);
		} catch (Exception e) {
			LOG.error("KafkaAsyncProducer instaned fail, Exception:" + producerConfig);
		}
	}

	@Override
	public void send(MessageHeader header, byte[] data) {
		try {
			producerRecordWrapper = producerRecordWrapper.getProducerRecordWrapper(header, data);
		} catch (Exception e) {
			LOG.error("send before constract producerRecordWrapper  is null ");
		}
		int partitionId = producerRecordWrapper.getPartitionId();

		linkedTransferQueue = PARTITION_SENDQUEU_MAP.get(partitionId);
		if (linkedTransferQueue != null) {
			linkedTransferQueue.put(producerRecordWrapper);
		}
		if (linkedTransferQueue == null) {
			linkedTransferQueue = new LinkedTransferQueue<>();
			PARTITION_SENDQUEU_MAP.put(partitionId, linkedTransferQueue);
			asyncSendWrapper = this.asyncSendWrapper.clone();

			sendDataThread = new SendDataThread(linkedTransferQueue, asyncSendWrapper);
			sendDataThread.setName("sendDataThread-" + partitionId);
			sendDataThread.start();
			sendDataThreads.add(sendDataThread);
			linkedTransferQueue.put(producerRecordWrapper);
		}

	}
	
	@Override
	public void close() {
		int threadSize = sendDataThreads.size();
		countDownLatch = new CountDownLatch(threadSize);
		LOG.info("KafkaAsyncProducer close!");
		while (threadSize > 0) {
			sendDataThread = sendDataThreads.get(threadSize - 1);
			int queueSize = sendDataThread.getQueueSize();
			//	LOG.info("current threadName = " + sendDataThread.getName() + ", queueSize = " + queueSize);
			if (queueSize == 0) {
				countDownLatch.countDown();
				sendDataThread.dispose();
				LOG.info("current threadName = " + sendDataThread.getName() + ", queueSize = " + queueSize);
				threadSize--;
			}else{
				
			}
		}
		try {
			LOG.info("threadSize = " + threadSize);
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		asyncSendWrapper.close();
	}
}
