package org.kafka.async.producer;

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
 * @author Devonmusa 2017年2月18日 上午1:43:32
 *
 */
public class KafkaAsyncProducer extends KafkaSenderStrategy {
	private static final Logger log = LoggerFactory.getLogger(KafkaAsyncProducer.class);
	private LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private List<PartitionInfo> partitionInfos;
	private KafkaSendWrapper sendWrapper;
	private String topic;

	public static final Map<Integer, LinkedTransferQueue<ProducerRecordWrapper>> PARTITION_SENDQUEU_MAP = new ConcurrentHashMap<>();

	public KafkaAsyncProducer(KafkaProducerConfig producerConfig) {
		if (producerConfig == null) {
			log.error("KafkaAsyncProducer instaned fail, Exception:" + producerConfig);
			return;
		}
		
		try {
			sendWrapper = new KafkaSendWrapper(producerConfig);
			partitionInfos = sendWrapper.getPartitionInfos();
			topic = producerConfig.getTopic();
			producerRecordWrapper = new ProducerRecordWrapper(topic, partitionInfos);
		} catch (Exception e) {
			log.error("KafkaAsyncProducer instaned fail, Exception:" + producerConfig);
		}
	}

	@Override
	public void send(MessageHeader header, byte[] data) {
		try {
			producerRecordWrapper = producerRecordWrapper.getProducerRecordWrapper(header, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int partitionId = producerRecordWrapper.getPartitionId();

		linkedTransferQueue = PARTITION_SENDQUEU_MAP.get(partitionId);
		if (linkedTransferQueue == null) {
			linkedTransferQueue = new LinkedTransferQueue<>();
			PARTITION_SENDQUEU_MAP.put(partitionId, linkedTransferQueue);
			SendDataThread sendDataThread = new SendDataThread(linkedTransferQueue);
			sendDataThread.start();
		}
		linkedTransferQueue.put(producerRecordWrapper);
	}

	@Override
	public void close() {
		sendWrapper.close();
		
	}
}
