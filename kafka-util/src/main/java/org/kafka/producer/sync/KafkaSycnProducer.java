package org.kafka.producer.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
public class KafkaSycnProducer extends AbstractSenderStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaSycnProducer.class);

	public static final Map<Integer, LinkedTransferQueue<ProducerRecordWrapper>> PARTITION_SENDQUEU_MAP = new ConcurrentHashMap<>();
	private final List<SendDataThread> sendDataThreads = new ArrayList<>();
	private LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private KafkaSyncSendWrapper syncSendWrapper;
	private List<PartitionInfo> partitionInfos;
	private SendDataThread sendDataThread;
	private String topic;

	public KafkaSycnProducer(KafkaProducerConfig producerConfig) throws Exception {

		syncSendWrapper = new KafkaSyncSendWrapper(producerConfig);
		partitionInfos = syncSendWrapper.getPartitionInfos();
		topic = producerConfig.getTopic();
		producerRecordWrapper = new ProducerRecordWrapper(topic, partitionInfos);
	}

	@Override
	public void send(MessageHeader header, byte[] data) {
		try {

			producerRecordWrapper = producerRecordWrapper.getProducerRecordWrapper(header, data);
		} catch (Exception e) {
			LOG.error("producerRecordWrapper Exception:" + e);
		}
		int partitionId = producerRecordWrapper.getPartitionId();
		linkedTransferQueue = PARTITION_SENDQUEU_MAP.get(partitionId);

		if (linkedTransferQueue != null) {
			linkedTransferQueue.put(producerRecordWrapper);
		}
		if (linkedTransferQueue == null) {
			linkedTransferQueue = new LinkedTransferQueue<>();
			PARTITION_SENDQUEU_MAP.put(partitionId, linkedTransferQueue);

			syncSendWrapper = this.syncSendWrapper.clone();

			sendDataThread = new SendDataThread(linkedTransferQueue, syncSendWrapper);
			sendDataThread.setName("sendDataThread-" + partitionId);
			sendDataThread.start();
			sendDataThreads.add(sendDataThread);
			linkedTransferQueue.put(producerRecordWrapper);
		}

	}

	@Override
	public void close() {
		int queueSize = sendDataThreads.size();
		for (int i = 0; i < queueSize; i++) {
			sendDataThread = sendDataThreads.get(i);
			if (sendDataThread.getQueueSize() == 0) {
				sendDataThread.dispose();
				sendDataThreads.remove(sendDataThread);
				queueSize = sendDataThreads.size();
				LOG.info(" Thread close info, " + SendDataThread.currentThread() + "is stop, will need to close thread=" + sendDataThreads);
			} else if (queueSize != 0) {
				i = 0;
			} else {
				break;
			}
		}
		syncSendWrapper.close();
	}
}
