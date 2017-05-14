package org.kafka.proxy;


import org.kafka.common.KafkaExecuteStrategy;
import org.kafka.consumer.async.KafkaAsyncConsumer;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.consumer.sync.KafkaSyncConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public class KafkaReceiveProxy {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaReceiveProxy.class);
	
	private KafkaReceiveStrategegy receiveStrategegy;
	
	public KafkaReceiveProxy(KafkaConsumerConfig config, KafkaExecuteStrategy executeMode) {
		try {
			switch (executeMode) {
			case Async:
				setReceiveStrategey(new KafkaAsyncConsumer(config));
				break;
			case Sync:
				setReceiveStrategey(new KafkaSyncConsumer(config));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			LOG.error("KafkaSender Exception:" + e);
		}
	}
	
	public void receive() {
		receiveStrategegy.receive();
	}
	
	public void close() {
		receiveStrategegy.close();
	}
	
	public void setReceiveStrategey(KafkaReceiveStrategegy receiveStrategegy) {
		this.receiveStrategegy = receiveStrategegy;
	}
	
}
