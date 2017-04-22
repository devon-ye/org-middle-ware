package org.kafka.producer;

import java.util.List;

import org.apache.kafka.common.PartitionInfo;
import org.kafka.common.KafkaProducerConfig;
import org.kafka.common.KafkaSendWrapper;
import org.kafka.common.MessageHeader;
import org.kafka.producer.common.ProducerRecordWrapper;
import org.kafka.sender.KafkaSenderStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
*@author  Devonmusa
*@date 2017年2月18日 
*
*/
public class KafkaProducer extends KafkaSenderStrategy{
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	private ProducerRecordWrapper producerRecordWrapper;
	private List<PartitionInfo>  partitionInfos;
	private KafkaSendWrapper sendWrapper;
	private String topic;
	
	public  KafkaProducer(KafkaProducerConfig producerConfig) throws Exception{
		
		sendWrapper = new KafkaSendWrapper(producerConfig);
		partitionInfos = sendWrapper.getPartitionInfos();
		topic = producerConfig.getTopic();
		producerRecordWrapper = new ProducerRecordWrapper(topic,partitionInfos);
	}
	
	@Override
	public void send(MessageHeader header, byte[] data) {
		try {
			
			producerRecordWrapper =producerRecordWrapper.getProducerRecordWrapper(header, data);
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
