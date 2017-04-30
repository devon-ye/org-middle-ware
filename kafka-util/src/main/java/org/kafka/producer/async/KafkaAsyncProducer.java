package org.kafka.producer.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;

import org.apache.kafka.common.PartitionInfo;

import org.kafka.common.MessageHeader;
import org.kafka.producer.common.KafkaProducerConfig;
import org.kafka.producer.common.KafkaSendWrapper;
import org.kafka.producer.common.ProducerRecordWrapper;
import org.kafka.producer.common.SendDataThread;
import org.kafka.proxy.KafkaSenderStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaAsyncProducer extends KafkaSenderStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncProducer.class);

	private volatile LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private final List<SendDataThread> sendDataThreads = new ArrayList<>();
	private SendDataThread sendDataThread;
	private List<PartitionInfo> partitionInfos;
	private KafkaSendWrapper sendWrapper;
	private String topic;

	public static final Map<Integer, LinkedTransferQueue<ProducerRecordWrapper>> PARTITION_SENDQUEU_MAP = new ConcurrentHashMap<>();

	public KafkaAsyncProducer(KafkaProducerConfig producerConfig) {
		if (producerConfig == null) {
			LOG.error("KafkaAsyncProducer instaned fail, Exception:" + producerConfig);
			return;
		}

		try {
			sendWrapper = new KafkaSendWrapper(producerConfig);
			partitionInfos = sendWrapper.getPartitionInfos();
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
			sendWrapper = this.sendWrapper.clone();

			sendDataThread = new SendDataThread(linkedTransferQueue, sendWrapper);
			sendDataThread.setName("sendDataThread-" + partitionId);
			sendDataThread.start();
			sendDataThreads.add(sendDataThread);
			linkedTransferQueue.put(producerRecordWrapper);
		}

	}

	@SuppressWarnings("static-access")
	@Override
	public void close() {
		int queueSize = sendDataThreads.size();
		for (int i = 0; i < queueSize; i++) {
			sendDataThread = sendDataThreads.get(i);
			if (sendDataThread.getQueueSize() == 0) {
				sendDataThread.dispose();
				sendDataThreads.remove(sendDataThread);
				queueSize = sendDataThreads.size();
				LOG.info(" Thread close info, " + sendDataThread.currentThread() + "is stop, will need to close thread=" + sendDataThreads);
			} else if (queueSize != 0) {
				i = 0;
			} else {
				break;
			}
		}
		sendWrapper.close();
	}
}
