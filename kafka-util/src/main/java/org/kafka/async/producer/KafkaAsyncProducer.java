package org.kafka.async.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.kafka.async.common.MessageHeader;
import org.kafka.async.common.SendMessageQueue;
import org.kafka.sender.KafkaSenderStrategy;

import kafka.common.Config;

/**
*@see
*@author  Devonmusa
*@version
*2017年2月18日 上午1:43:32
*
*/
public class KafkaAsyncProducer extends KafkaSenderStrategy{
	
	private SendMessageQueue sendMessageQueue;
	
	public KafkaAsyncProducer(Properties props) {
		sendMessageQueue = new  SendMessageQueue(props);
	}
	
	@Override
	public void send(MessageHeader header,byte[] data){
		sendMessageQueue.sendMessage(header, data);
	}
	
	@Override
	public void close(){

	}
}
