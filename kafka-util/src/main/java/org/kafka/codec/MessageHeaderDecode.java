package org.kafka.codec;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.kafka.common.MessageHeader;

/**
*@author  Devonmusa
*@date 2017年4月2日
*/
public class MessageHeaderDecode implements Deserializer<MessageHeader> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MessageHeader deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
