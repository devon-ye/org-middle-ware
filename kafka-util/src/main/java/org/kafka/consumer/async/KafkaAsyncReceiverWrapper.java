package org.kafka.consumer.async;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.kafka.codec.MessageHeaderDecode;
import org.kafka.common.MessageHeader;
import org.kafka.consumer.common.KafkaConsumerConfig;

/**
 *
 * @author Devonmusa
 * @date 2017年5月13日
 */
public class KafkaAsyncReceiverWrapper implements Cloneable {

	
	private KafkaConsumer<MessageHeader, byte[]> kafkaConsumer;
	private KafkaConsumerConfig consumerConfig;

	public KafkaAsyncReceiverWrapper(KafkaConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}

	protected void receive() {
		
	}
	
	private void init() {

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
