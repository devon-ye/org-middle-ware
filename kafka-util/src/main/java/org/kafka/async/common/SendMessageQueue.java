package org.kafka.async.common;

import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;

import org.apache.kafka.clients.producer.KafkaProducer;

/**
*@Describetion
*@author  Devonmusa
*@date 2017年4月1日
*/
public class SendMessageQueue{
	private KafkaProducer producer;
	private final LinkedTransferQueue<SendMessage> messageQueue = new LinkedTransferQueue<SendMessage>();
	
	public void sendMessage(){
		SendMessage sendMessage = messageQueue.poll();
	}
	
	private class SendThread extends Thread{
		
		public void run(){
			//producer.send(record);
			//producer.send(record, callback)
		}
	}
	
}
