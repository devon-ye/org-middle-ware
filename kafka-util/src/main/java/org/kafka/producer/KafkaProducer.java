package org.kafka.producer;

import java.util.Properties;

import org.kafka.async.common.MessageHeader;
import org.kafka.common.KafkaProducerConfig;
import org.kafka.common.KafkaSendWrapper;
import org.kafka.producer.common.ProducerRecordWrapper;
import org.kafka.sender.KafkaSenderStrategy;


/**
*@author  Devonmusa
*@date 2017年2月18日 上午1:42:04
*
*/
public class KafkaProducer extends KafkaSenderStrategy{
	private ProducerRecordWrapper producerRecordWrapper;
	private KafkaProducerConfig producerConfig;
	private KafkaSendWrapper sendWrapper;
	
	public  KafkaProducer(KafkaProducerConfig config){
		
		sendWrapper = new KafkaSendWrapper(producerConfig);
	}
	
	@Override
	public void send(MessageHeader header, byte[] data) {
		producerRecordWrapper = new ProducerRecordWrapper(header,data);
		
		sendWrapper.send(producerRecordWrapper);
	}

	@Override
	public void close() {
		sendWrapper.close();
	}
}
