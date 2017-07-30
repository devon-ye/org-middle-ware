package org.kafka.proxy;

import org.kafka.common.IMessageListener;
import org.kafka.common.KafkaExecuteStrategy;
import org.kafka.consumer.async.KafkaAsyncConsumer;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.consumer.sync.KafkaSyncConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaReceiveProxy {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaReceiveProxy.class);

	private AbstrctReceiveStrategy receiveStrategegy;

	public KafkaReceiveProxy(KafkaConsumerConfig config, KafkaExecuteStrategy executeStrategy) {
		try {
			switch (executeStrategy) {
			case ASYNC:
				setReceiveStrategey(new KafkaAsyncConsumer(config));
				break;
			case SYNC:
				setReceiveStrategey(new KafkaSyncConsumer(config));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			LOG.error("KafkaReceiver Exception:" + e);
		}
	}

	public void receive(IMessageListener imessageListener) {
		receiveStrategegy.receive(imessageListener);
	}

	public void receive(int partitionId) {
		receiveStrategegy.receive();
	}

	public void close() {
		receiveStrategegy.close();
	}

	private void setReceiveStrategey(AbstrctReceiveStrategy receiveStrategegy) {
		this.receiveStrategegy = receiveStrategegy;
	}

}
