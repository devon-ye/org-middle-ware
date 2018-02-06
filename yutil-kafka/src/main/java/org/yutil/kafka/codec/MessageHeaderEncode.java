package org.yutil.kafka.codec;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.kafka.common.serialization.Serializer;
import org.yutil.kafka.common.MessageHeader;
import org.yutil.kafka.common.ProtoMessageHeader;


/**
*
*@author  Devonmusa
*@date  2017年4月2日
*/
public class MessageHeaderEncode implements Serializer<MessageHeader>{
//	private static final Logger LOG = LoggerFactory.getLogger(MessageHeaderEncode.class);
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public byte[] serialize(String topic, MessageHeader value) {
		ProtoMessageHeader.MessageHeader.Builder builder = ProtoMessageHeader.MessageHeader.newBuilder();
		builder.setProducerId(value.getProducerId());
		builder.setKey(value.getKey());
		builder.setType(value.getType());
		builder.setTimestamp(value.getTimestamp());
		Map<String, String> attributeMap = value.getAttributeMap();
		if (attributeMap != null && attributeMap.size() > 0) {
			for (Entry<String, String> entry : attributeMap.entrySet()) {
				 ProtoMessageHeader.MessageHeader.AttributeEntry.Builder attributeBuilder = ProtoMessageHeader.MessageHeader.AttributeEntry.newBuilder();
				 attributeBuilder.putAllAttributeMap((Map<String, String>) entry);
				builder.addAttributeEntry(attributeBuilder.build());
			}
		}
		return builder.build().toByteArray();
	}

	@Override
	public void close() {
		
	}

}
