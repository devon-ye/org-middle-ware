package org.mw.kafka.consumer.common;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mw.kafka.common.MessageHeader;

/**
*
*@author Devonmusa
*@date   2017年5月13日
*/
public class ConsumerRecordsWrapper {
	
	private ConsumerRecord<MessageHeader,byte[]> consumerRecord;

	public ConsumerRecord<MessageHeader, byte[]> getConsumerRecord() {
		return consumerRecord;
	}

	public void setConsumerRecord(ConsumerRecord<MessageHeader, byte[]> consumerRecord) {
		this.consumerRecord = consumerRecord;
	}	
}
