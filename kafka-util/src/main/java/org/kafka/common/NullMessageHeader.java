package org.kafka.common;

import java.util.Map;

/**
 *
 * @author Devonmusa
 * @date 2017年4月29日
 */
public class NullMessageHeader extends MessageHeader {

	
	
	public NullMessageHeader(long key, int type) {
		super(key, type);
	}

	public NullMessageHeader(long key, int partition, int type) {
		super(key, partition, type);
	}

	public NullMessageHeader(long key, int type, Map<String, String> attributeMap) {
		super(key, type, attributeMap);

	}

}
