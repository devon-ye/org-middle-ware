package org.kafka.proxy;


import org.kafka.common.KafkaExecuteMode;
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
	private static final Logger log = LoggerFactory.getLogger(KafkaReceiveProxy.class);
	
	private KafkaReceiveStrategegy receiveStrategegy;
	
	public KafkaReceiveProxy(KafkaConsumerConfig config, KafkaExecuteMode executeMode) {
		try {
			switch (executeMode) {
			case Async:
				setReceiveStrategey(new KafkaAsyncConsumer());
				break;
			case Sync:
				setReceiveStrategey(new KafkaSyncConsumer());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			log.error("KafkaSender Exception:" + e);
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
