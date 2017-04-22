package org.kafka.common;

import java.util.Map;

/**
*@Describetion
*@author  Devonmusa
*@date 2017年4月1日
*/
public class MessageHeader {
	
	/**
	 * 可用Builder构建器替代下面的多参构造方法
	 * @author devonmusa
	 *
	 */
	public static class Builder{
		private final long key;
		
		private  int type;
		private long offset;
		private int partition;
		private long timetamp;
		private int producerId;
		private Map<String, String> attributeMap;
		
		public Builder(long key){
			this.key = key;
		}
		
		public Builder type(int val){
			type = val;
			return this;
		}
		
		public Builder partition(int val){
			partition = val;
			return this;
		}
		
		public Builder offset(int val){
			offset = val;
			return this;
		}
		
		public Builder timetamp(int val){
			timetamp = val;
			return this;
		}
		
		public Builder producerId(int val){
			producerId = val;
			return this;
		}
		
		public Builder attributeMap(Map<String, String> val){
			attributeMap = val;
			return this;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MessageHeader [key=").append(key).append(", type=").append(type).append(", offset=").append(offset).append(", timetamp=").append(timetamp).append(", partition=")
				.append(partition).append(", producerId=").append(producerId).append(", attributeMap=").append(attributeMap).append("]");
			return builder.toString();
		}
	}
	
	/**
	 * 重叠构造器可用上面构建器代替
	 * @param key
	 * @param type
	 */
	private long key;
	private int type;
	private long offset;
	private long timetamp;
	private int partition;
	private long producerId;
	private Map<String, String> attributeMap;
	public MessageHeader(long key, int type) {
		this.key = key;
		this.type = type;
	}

	public MessageHeader(int partition,int type) {
		this.type = type;
		this.partition = partition;
	}
	
	public MessageHeader(long key, int type, Map<String, String> attributeMap) {
		super();
		this.key = key;
		this.type = type;
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

	public long getTimetamp() {
		return timetamp;
	}

	public int getPartition() {
		return partition;
	}

	public long getProducerId() {
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

	public void setTimetamp(long timetamp) {
		this.timetamp = timetamp;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public void setProducerId(long producerId) {
		this.producerId = producerId;
	}

	public void setAttributeMap(Map<String, String> attributeMap) {
		this.attributeMap = attributeMap;
	}
}
