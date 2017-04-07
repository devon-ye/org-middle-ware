package org.kafka.async.common;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedTransferQueue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.kafka.common.KafkaProducerConfig;

/**
*@see
*@author  Devonmusa
*@date 2017年4月1日
*/
public class SendMessageQueue{
	
	private KafkaProducerConfig producerConfig;
	
	private KafkaProducer producer;
	 
	private Properties props;
	
	private final LinkedTransferQueue<SendMessage> messageQueue = new LinkedTransferQueue<SendMessage>();
	
	public SendMessageQueue(Properties props){
		//producerConfig = new KafkaProducerConfig(props);
		if(producer != null){
			this.connect();
		}
	}
	
	public void sendMessage(MessageHeader header,byte[] data){
		SendMessage sendMessage = new SendMessage(header, data);
		messageQueue.put(sendMessage);
	}
	
	private class SendThread extends Thread{
		
		public void run(){
			
		}
	}
	
	private void connect(){
		producer = new KafkaProducer<>(props);
	}
	
}
