package org.kafka.producer.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
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
public class KafkaSycnProducer extends KafkaSenderStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaSycnProducer.class);
	
	public static final Map<Integer, LinkedBlockingQueue<ProducerRecordWrapper>> PARTITION_SENDQUEU_MAP = new ConcurrentHashMap<>();
	private final List<SendDataThread> sendDataThreads = new ArrayList<>();
	private final int LINKEDBLOCKINGQUEUE_MAX_SIXE = 100000;
	private LinkedBlockingQueue<ProducerRecordWrapper> linkedBlockingQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private SendDataThread sendDataThread;
	private List<PartitionInfo> partitionInfos;
	private KafkaSendWrapper sendWrapper;
	private String topic;

	
	public KafkaSycnProducer(KafkaProducerConfig producerConfig) throws Exception {

		sendWrapper = new KafkaSendWrapper(producerConfig);
		partitionInfos = sendWrapper.getPartitionInfos();
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
		linkedBlockingQueue = PARTITION_SENDQUEU_MAP.get(partitionId);
		
		if(linkedBlockingQueue != null) {
			try {
				linkedBlockingQueue.put(producerRecordWrapper);
			} catch (InterruptedException e) {
				LOG.error("linkedBlockingQueue Exception:" + e +"queueSize="+linkedBlockingQueue.size());
			}
		}
		if (linkedBlockingQueue == null) {
			linkedBlockingQueue = new LinkedBlockingQueue<>(LINKEDBLOCKINGQUEUE_MAX_SIXE);
			PARTITION_SENDQUEU_MAP.put(partitionId, linkedBlockingQueue);
			
			sendWrapper = this.sendWrapper.clone();
			
			sendDataThread = new SendDataThread(linkedBlockingQueue, sendWrapper);
			sendDataThread.setName("sendDataThread-" + partitionId);
			sendDataThread.start();
			sendDataThreads.add(sendDataThread);
			try {
				linkedBlockingQueue.put(producerRecordWrapper);
			} catch (InterruptedException e) {
				LOG.error("linkedBlockingQueue Exception:" + e +"queueSize="+linkedBlockingQueue.size());
			}
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
