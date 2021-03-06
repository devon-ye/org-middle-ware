package org.mw.kafka.proxy;


import org.mw.kafka.common.KafkaExecuteStrategy;
import org.mw.kafka.common.MessageHeader;
import org.mw.kafka.producer.async.KafkaAsyncProducer;
import org.mw.kafka.producer.common.KafkaProducerConfig;
import org.mw.kafka.producer.sync.KafkaSycnProducer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Devonmusa
 * @date 2017年4月2日
 */
public class KafkaSenderProxy {
	private Logger logger = LoggerFactory.getLogger(KafkaSenderProxy.class);
	
	private AbstractSenderStrategy senderStrategy;

	public KafkaSenderProxy(KafkaProducerConfig config, KafkaExecuteStrategy executeMode) {
		try {
			switch (executeMode) {
			case ASYNC:
				setSenderStrategy(new KafkaAsyncProducer(config));
				break;
			case SYNC:
				setSenderStrategy(new KafkaSycnProducer(config));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("KafkaSender Exception:" + e);
		}
	}

	public void send(MessageHeader header, byte[] data) {
		senderStrategy.send(header, data);
	}

	public void close() {
		logger.info("KafkaSenderProxy close!");
		senderStrategy.close();
	}

	private void setSenderStrategy(AbstractSenderStrategy senderStrategy) {
		this.senderStrategy = senderStrategy;
	}
}
