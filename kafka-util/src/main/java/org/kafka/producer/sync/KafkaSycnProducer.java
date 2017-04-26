package org.kafka.producer.sync;

import java.util.List;

import org.apache.kafka.common.PartitionInfo;

import org.kafka.common.MessageHeader;
import org.kafka.producer.common.KafkaProducerConfig;
import org.kafka.producer.common.KafkaSendWrapper;
import org.kafka.producer.common.ProducerRecordWrapper;
import org.kafka.proxy.KafkaSenderStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaSycnProducer extends KafkaSenderStrategy {
	private static final Logger logger = LoggerFactory.getLogger(KafkaSycnProducer.class);
	private ProducerRecordWrapper producerRecordWrapper;
	private List<PartitionInfo> partitionInfos;
	private KafkaSendWrapper sendWrapper;
	private String topic;

	public KafkaSycnProducer(KafkaProducerConfig producerConfig) throws Exception {

		sendWrapper = new KafkaSendWrapper(producerConfig);
		partitionInfos = sendWrapper.getPartitionInfos();
		topic = producerConfig.getTopic();
		producerRecordWrapper = new ProducerRecordWrapper(topic, partitionInfos);
	}

	@Override
	public void send(MessageHeader header, byte[] data) {
		try {

			producerRecordWrapper = producerRecordWrapper.getProducerRecordWrapper(header, data);
		} catch (Exception e) {
			logger.error("producerRecordWrapper Exception:" + e);
		}

		try {
			sendWrapper.send(producerRecordWrapper);
		} catch (Exception e) {
			logger.error("send Exception:" + e);
		}
	}

	@Override
	public void close() {
		sendWrapper.close();
	}
}
