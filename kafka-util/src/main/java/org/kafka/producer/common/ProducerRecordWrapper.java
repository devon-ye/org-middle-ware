package org.kafka.producer.common;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.kafka.common.MessageHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月5日
 */
public class ProducerRecordWrapper {
	
	private static final Logger log = LoggerFactory.getLogger(ProducerRecordWrapper.class);

	private ProducerRecord<MessageHeader, byte[]> producerRecord;
	private int partitionSize;
	private int partitionId;
	private String topic;

	public ProducerRecordWrapper(String topic, List<PartitionInfo> partitionInfos) throws Exception {
		this.topic = topic;
		this.partitionSize = partitionInfos.size();
	}

	public ProducerRecordWrapper getProducerRecordWrapper(MessageHeader messageHeader, byte[] value) throws Exception {
		if (topic == null) {
			log.error("getProducerRecordWrapper, topic is null!!!");
			throw new Exception("topic is null!!!");
		}

		long key = messageHeader.getKey();
		int tempPartitionId = messageHeader.getPartitionId();
		if (tempPartitionId == -1 && partitionSize != 0) {
			tempPartitionId = (int) (key / partitionSize);
		}
		this.partitionId = tempPartitionId;
		long timestamp = System.currentTimeMillis();
		producerRecord = new ProducerRecord<MessageHeader, byte[]>(topic, partitionId, timestamp, messageHeader, value);
		return this;
	}

	public ProducerRecord<MessageHeader, byte[]> getProducerRecord() {
		return producerRecord;
	}

	public int getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(int partitionId) {
		this.partitionId = partitionId;
	}
}
