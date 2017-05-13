package org.kafka.consumer.sync;

import org.kafka.consumer.async.KafkaAsyncConsumer;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.proxy.KafkaReceiveStrategegy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public class KafkaSyncConsumer extends KafkaReceiveStrategegy {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncConsumer.class);
	
	private KafkaConsumerConfig consumerConfig;
	
	public KafkaSyncConsumer(KafkaConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}
	
	@Override
	public void receive() {
		

	}

	@Override
	public void close() {

	}
	
}
