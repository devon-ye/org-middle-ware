package org.kafka.codec;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.kafka.common.MessageHeader;

/**
*@author  Devonmusa
*@date 2017年4月2日
*/
public class MessageHeaderEncode implements Serializer<MessageHeader>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String topic, MessageHeader data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
