package org.yutil.kafka.codec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.yutil.kafka.common.MessageHeader;
import org.yutil.kafka.common.ProtoMessageHeader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author Devonmusa
 * @date 2017年4月2日
 */
public class MessageHeaderDecode implements Deserializer<MessageHeader> {
	private static final Logger LOG = LoggerFactory.getLogger(MessageHeaderDecode.class);

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public MessageHeader deserialize(String topic, byte[] data) {
		MessageHeader messageHeader = null;

		try {
			ProtoMessageHeader.MessageHeader value = ProtoMessageHeader.MessageHeader.parseFrom(data);
			long key = value.getKey();
			int type = value.getType();
			messageHeader = new MessageHeader(key, type);
			messageHeader.setOffset(value.getOffset());
			messageHeader.setTimestamp(value.getTimestamp());
			messageHeader.setPartitionId(value.getPartitionId());
			messageHeader.setProducerId(value.getProducerId());

			if (value.getAttributeEntryCount() > 0) {
				Map<String, String> attributeMap = new HashMap<String, String>();
				List<ProtoMessageHeader.MessageHeader.AttributeEntry> attributeEntries = value.getAttributeEntryList();
				for (ProtoMessageHeader.MessageHeader.AttributeEntry entry : attributeEntries) {
					attributeMap.putAll(entry.getAttributeMapMap());
				}
				messageHeader.setAttributeMap(attributeMap);
			}
			return messageHeader;
		} catch (InvalidProtocolBufferException e) {
			LOG.error("deserialize failed! InvalidProtocolBufferException:" + e);
		} catch (Throwable e) {
			LOG.error("deserialize failed! Throwable:" + e);
		}
		LOG.error("MessageHeader deserialize failed! return null!");
		return null;
	}

	@Override
	public void close() {

	}

}
