package org.kafka.consumer.async;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.kafka.common.MessageHeader;
import org.kafka.consumer.common.KafkaConsumerConfig;

/**
 *
 * @author Devonmusa
 * @date 2017年5月13日
 */
public class KafkaAsyncReceiverWrapper implements Cloneable {

	
	private KafkaConsumer<MessageHeader, byte[]> kafkaConsumer;
	private List<PartitionInfo>  partitionInfos;
	private KafkaConsumerConfig consumerConfig;
	private Properties props;

	public KafkaAsyncReceiverWrapper(KafkaConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}

	protected void receive() {
	
	}
	
	private void init() {
		partitionInfos = kafkaConsumer.partitionsFor(consumerConfig.getTopic());
	}

	private void connect() {
		
		kafkaConsumer = new KafkaConsumer<>(consumerConfig.getProperties());
		
	}
	
	private void reTryConnect() {

	}
	

	
	public void close() {

	}
	
	public KafkaConsumer<MessageHeader, byte[]> getKafkaConsumer() {
		return kafkaConsumer;
	}

	

}
