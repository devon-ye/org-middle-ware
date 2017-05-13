package org.kafka.consumer.async;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.kafka.common.MessageHeader;

/**
*
*@author Devonmusa
*@date   2017年5月7日
*/
public class KafkaAsyncRecevierThread extends  Thread {

	private KafkaConsumer<MessageHeader, byte[]> kafkaConsumer;
	private ConsumerRecords<MessageHeader, byte[]> consumerRecords;
	public volatile boolean isRunning = false;
	
	public KafkaAsyncRecevierThread(KafkaAsyncReceiverWrapper asyncReceiverWrapper){
		this.kafkaConsumer = asyncReceiverWrapper.getKafkaConsumer();
	}
	
	public void run() {
		isRunning =  true;
		while(isRunning) {
			consumerRecords = kafkaConsumer.poll(100);
			for(ConsumerRecord<MessageHeader, byte[]> consumerRecord:consumerRecords) {
				consumerRecord.value();
				consumerRecord.key();
			}
			
		}
	}
	
	public void close() {
		isRunning = false;
	}
}
