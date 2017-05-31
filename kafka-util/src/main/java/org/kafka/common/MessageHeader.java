package org.kafka.common;

import java.util.Map;

/**
 * @Describetion
 * @author Devonmusa
 * @date 2017年4月1日
 */
public class MessageHeader {
	// private static final Logger LOG =
	// LoggerFactory.getLogger(MessageHeader.class);
	/**
	 * 可用Builder构建器替代下面的多参构造方法
	 * 
	 * @author devonmusa
	 *
	 */
	public static class Builder {
		private final long key;

		private int type;
		private long offset;
		private long timestamp;
		private int producerId;
		private int partitionId = -1;
		private Map<String, String> attributeMap;

		public Builder(long key) {
			this.key = key;
		}

		public Builder type(int val) {
			type = val;
			return this;
		}

		public Builder partition(int val) {
			partitionId = val;
			return this;
		}

		public Builder offset(int val) {
			offset = val;
			return this;
		}

		public Builder timetamp(int val) {
			timestamp = val;
			return this;
		}

		public Builder producerId(int val) {
			producerId = val;
			return this;
		}

		public Builder attributeMap(Map<String, String> val) {
			attributeMap = val;
			return this;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MessageHeader [key=").append(key).append(", type=").append(type).append(", offset=").append(offset).append(", timestamp=").append(timestamp).append(", partitionId=")
				.append(partitionId).append(", producerId=").append(producerId).append(", attributeMap=").append(attributeMap).append("]");
			return builder.toString();
		}
	}

	/**
	 * 重叠构造器可用上面构建器代替
	 * 
	 * @param key
	 * @param type
	 */
	private long key;
	private int type;
	private long offset;
	private long timestamp;
	private int partitionId = -1;
	private int producerId;
	private Map<String, String> attributeMap;

	public MessageHeader(long key, int type) {
		this(key, type, -1);
	}

	public MessageHeader(long key, int type, int partitionId) {
		this(key, type, partitionId, null);
	}

	public MessageHeader(long key, int type, int partitionId, Map<String, String> attributeMap) {
		this.key = key;
		this.type = type;
		this.partitionId = partitionId;
		this.attributeMap = attributeMap;
	}

	public long getKey() {
		return key;
	}

	public int getType() {
		return type;
	}

	public long getOffset() {
		return offset;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getPartitionId() {
		return partitionId;
	}

	public int getProducerId() {
		return producerId;
	}

	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setPartitionId(int partitionId) {
		this.partitionId = partitionId;
	}

	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}

	public void setAttributeMap(Map<String, String> attributeMap) {
		this.attributeMap = attributeMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageHeader [key=").append(key).append(", type=").append(type).append(", offset=").append(offset).append(", timetamp=").append(timestamp).append(", partition=")
			.append(partitionId).append(", producerId=").append(producerId).append(", attributeMap=").append(attributeMap).append("]");
		return builder.toString();
	}
}
