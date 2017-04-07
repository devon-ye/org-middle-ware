package org.kafka.sender;

import java.util.Properties;

import org.kafka.async.common.MessageHeader;
import org.kafka.async.producer.KafkaAsyncProducer;
import org.kafka.common.KafkaProducerConfig;
import org.kafka.producer.KafkaProducer;

/**
*@see
*@author  Devonmusa
*@date 2017年4月2日
*/
public class KafkaSender {
	private KafkaSenderStrategy senderStrategy;
	
	public KafkaSender(KafkaProducerConfig config,KafkaSendMode sendMode){
		if(sendMode.value==1){
			setSenderStrategy(new KafkaAsyncProducer(config));
		}else{
			setSenderStrategy(new KafkaProducer(config));
		}
	}
	
	public void send(MessageHeader header,byte[] data){
		senderStrategy.send(header, data);
	}
	
	public void close() {
		senderStrategy.close();
	}
	
	private void setSenderStrategy(KafkaSenderStrategy senderStrategy) {
		this.senderStrategy = senderStrategy;
	}
}
