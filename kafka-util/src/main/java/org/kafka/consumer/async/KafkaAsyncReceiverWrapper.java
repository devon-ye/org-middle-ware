package org.kafka.consumer.async;

import java.util.List;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.kafka.common.MessageHeader;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年5月13日
 */
public class KafkaAsyncReceiverWrapper implements Cloneable {
	private final static Logger LOG = LoggerFactory.getLogger(KafkaAsyncReceiverWrapper.class);
	private KafkaConsumer<MessageHeader, byte[]> kafkaConsumer;
	private List<PartitionInfo> partitionInfos;
	private KafkaConsumerConfig consumerConfig;
	private volatile int reTryCount = 0;
	
	private final static int MAX_RECONNET_TIMES = 5;

	public KafkaAsyncReceiverWrapper(KafkaConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
		init();
	}

	protected void receive() {

	}

	public void close() {

	}

	public KafkaConsumer<MessageHeader, byte[]> getKafkaConsumer() {
		return kafkaConsumer;
	}

	private void init() {

		connect();
		getPartitionInfos();

	}

	private void connect() {
		try {
			kafkaConsumer = new KafkaConsumer<>(consumerConfig.getProperties());
		} catch (Exception e) {
			reTryConnect();
		}

	}

	private void reTryConnect() {
		reTryCount++;
		if (kafkaConsumer != null) {
			kafkaConsumer = null;
		}
		if(reTryCount <= MAX_RECONNET_TIMES) {
			connect();
		}else{
			LOG.error("Kafka Consumer reConncet  +"+reTryCount+"+ failed");
		}

	}

	private List<PartitionInfo> getPartitionInfos() {
		partitionInfos = kafkaConsumer.partitionsFor(consumerConfig.getTopic());
		return partitionInfos;
	}
}
