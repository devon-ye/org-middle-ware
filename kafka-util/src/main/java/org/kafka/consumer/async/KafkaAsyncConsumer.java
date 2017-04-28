package org.kafka.consumer.async;

import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.proxy.KafkaReceiveStrategegy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public class KafkaAsyncConsumer extends KafkaReceiveStrategegy {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncConsumer.class);
	private KafkaConsumerConfig consumerConfig;
	public KafkaAsyncConsumer(KafkaConsumerConfig conumerConfig) {
		this.consumerConfig = consumerConfig;
	}
	
	@Override
	public void receive() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
	/**
	*@function:
	*
	*/
}
