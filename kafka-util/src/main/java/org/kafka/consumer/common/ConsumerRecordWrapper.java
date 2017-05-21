package org.kafka.consumer.common;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kafka.common.MessageHeader;

/**
*
*@author Devonmusa
*@date   2017年5月13日
*/
public class ConsumerRecordWrapper {
	
	private ConsumerRecord<MessageHeader,byte[]> consumerRecord;

	public ConsumerRecord<MessageHeader, byte[]> getConsumerRecord() {
		return consumerRecord;
	}

	public void setConsumerRecord(ConsumerRecord<MessageHeader, byte[]> consumerRecord) {
		this.consumerRecord = consumerRecord;
	}	
}
