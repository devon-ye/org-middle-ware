package org.mw.kafka.codec;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月9日
 */
public class ZkStringSerializer implements ZkSerializer {
	
	private static final Logger LOG = LoggerFactory.getLogger(ZkStringSerializer.class);

	@Override
	public byte[] serialize(Object data) throws ZkMarshallingError {
		String value = data + "";

		try {
			return value.getBytes("UTF-8");
		} catch (Exception e) {
			throw new ZkMarshallingError("serialize failed!" + e);
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws ZkMarshallingError {
		String value = null;
		try {
			value = new String(bytes, "UTF-8");
		} catch (Exception e) {
			throw new ZkMarshallingError("zk deserialize error:", e);
		}
		return value;
	}

}
