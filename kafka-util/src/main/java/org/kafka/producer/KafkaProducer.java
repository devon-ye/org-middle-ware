package org.kafka.producer;

import java.util.Properties;

import org.kafka.async.common.MessageHeader;
import org.kafka.sender.KafkaSenderStrategy;


/**
*@author  Devonmusa
*@date 2017年2月18日 上午1:42:04
*
*/
public class KafkaProducer extends KafkaSenderStrategy{
	
	public  KafkaProducer(Properties props){
		
	}
	
	@Override
	public void send(MessageHeader header, byte[] data) {
		
		
	}

	@Override
	public void close() {
		
	}
}
