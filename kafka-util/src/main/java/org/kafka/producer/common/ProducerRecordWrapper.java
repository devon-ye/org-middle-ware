package org.kafka.producer.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.kafka.async.common.MessageHeader;
import org.kafka.async.common.SendMessage;

/**
*@see
*@author  Devonmusa
*@date 2017年4月5日
*/
public class ProducerRecordWrapper {
	private byte[]  value;
	
	private SendMessage sendMessage;
	
	private MessageHeader messageHeader;
	
	private ProducerRecord<MessageHeader, byte[]>	producerRecord = null;
	
	public ProducerRecordWrapper(MessageHeader messageHeader,byte[] value){
		this.messageHeader = messageHeader;
		this.value = value;
	}
	
	public ProducerRecord<MessageHeader,byte[]> getProducerRecord(){
		MessageHeader header = null;
		try{
			messageHeader = producerRecord.key();
			value =producerRecord.value();
			if(null == sendMessage){
				return null;
			}
			
			header = sendMessage.getHeader();
			if(null == header){
				return null;
			}
			
			messageHeader.setPartition(header.getPartition());
			messageHeader.setKey(header.getKey());
			messageHeader.setProducerId(header.getProducerId());
			messageHeader.setType(header.getType());
			
			Map<String, String> headAttributeMap = header.getAttributeMap();
			if (headAttributeMap == null) {
				headAttributeMap = new HashMap<String, String>();	
			}
			messageHeader.setAttributeMap(headAttributeMap);
			value = sendMessage.getData();
			return producerRecord;
			
		}catch(Exception e){
			
		}
		return null;
	}

}
