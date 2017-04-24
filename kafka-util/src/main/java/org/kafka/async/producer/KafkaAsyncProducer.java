package org.kafka.async.producer;

import java.util.Properties;


import org.kafka.async.common.SendMessageQueue;
import org.kafka.common.KafkaProducerConfig;
import org.kafka.common.MessageHeader;
import org.kafka.sender.KafkaSenderStrategy;


/**
*@see
*@author  Devonmusa
*@version
*2017年2月18日 上午1:43:32
*
*/
public class KafkaAsyncProducer extends KafkaSenderStrategy{
	private Properties props;
	private SendMessageQueue sendMessageQueue;
	
	public KafkaAsyncProducer(KafkaProducerConfig config) {
		props = config.getProperties();
		//sendMessageQueue = new  SendMessageQueue(props);
	}
	
	@Override
	public void send(MessageHeader header,byte[] data){
		sendMessageQueue.sendMessage(header, data);
	}
	
	@Override
	public void close(){

	}
}
