package org.kafka.consumer.async;

import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.proxy.KafkaReceiveStrategegy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaAsyncConsumer extends KafkaReceiveStrategegy {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncConsumer.class);

	private KafkaAsyncReceiverWrapper kafkaAsyncReceiverWrapper;

	public KafkaAsyncConsumer(KafkaConsumerConfig conumerConfig) {
		kafkaAsyncReceiverWrapper = new KafkaAsyncReceiverWrapper(conumerConfig);
	}

	@Override
	public void receive() {
		LOG.info("Consumer start recevier...");
		kafkaAsyncReceiverWrapper.receive();
	
	}

	@Override
	public void close() {
		kafkaAsyncReceiverWrapper.close();
	}

}
