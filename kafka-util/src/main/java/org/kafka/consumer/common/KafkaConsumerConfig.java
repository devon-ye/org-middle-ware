package org.kafka.consumer.common;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public class KafkaConsumerConfig {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerConfig.class);
	
	private long offSet;
	private String  topic;
	private int partitionId;
	private Properties properties;
	
	private static KafkaConsumerConfig instance = new KafkaConsumerConfig();
	
	private KafkaConsumerConfig(){
		init();
	}
	
	public KafkaConsumerConfig getInstance() {
		return instance;
	}

	public long getOffSet() {
		return offSet;
	}

	public String getTopic() {
		return topic;
	}

	public int getPartitionId() {
		return partitionId;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setOffSet(long offSet) {
		this.offSet = offSet;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setPartitionId(int partitionId) {
		this.partitionId = partitionId;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void addProperty(String key,String value) {
		properties.put(key, value);
	}
	
	private void init() {
		
	}
	
	private Properties getConsumerConfig() {
		Properties props = new Properties();
		
		//TODO read  default consumer config
		
		if(properties != null) {
			props.putAll(properties);
		}
		return props;
	}
	
}
