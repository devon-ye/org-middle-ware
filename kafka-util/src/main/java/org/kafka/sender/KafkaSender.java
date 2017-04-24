package org.kafka.sender;

import org.kafka.async.producer.KafkaAsyncProducer;
import org.kafka.common.MessageHeader;
import org.kafka.producer.KafkaProducer;
import org.kafka.producer.common.KafkaProducerConfig;
import org.kafka.util.KafkaSendMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月2日
 */
public class KafkaSender {
	private Logger logger = LoggerFactory.getLogger(KafkaSender.class);
	private KafkaSenderStrategy senderStrategy;

	public KafkaSender(KafkaProducerConfig config, KafkaSendMode sendMode) {
		try {
			switch (sendMode) {
			case Async:
				setSenderStrategy(new KafkaAsyncProducer(config));
				break;
			case Sync:
				setSenderStrategy(new KafkaProducer(config));
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
		senderStrategy.close();
	}

	private void setSenderStrategy(KafkaSenderStrategy senderStrategy) {
		this.senderStrategy = senderStrategy;
	}
}
