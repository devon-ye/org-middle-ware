package org.kafka.consumer.sync;

import org.kafka.common.IMessageListener;
import org.kafka.consumer.async.KafkaAsyncConsumer;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.proxy.AbstrctReceiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public class KafkaSyncConsumer extends AbstrctReceiveStrategy {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncConsumer.class);
	
	private KafkaSyncRecevier kafkaSyncRecevier;
	
	public KafkaSyncConsumer(KafkaConsumerConfig consumerConfig) {
		kafkaSyncRecevier = new KafkaSyncRecevier(consumerConfig);
		LOG.info(" init finshed");
	}
	
	@Override
	public void receive() {
		
		kafkaSyncRecevier.receive();
	}

	@Override
	public void close() {

	}

	@Override
	public void receive(IMessageListener imessageListener) {
		// TODO Auto-generated method stub
		
	}
	
}
