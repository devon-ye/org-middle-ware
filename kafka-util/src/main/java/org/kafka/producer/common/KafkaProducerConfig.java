package org.kafka.producer.common;

import java.util.Properties;

/**
*@see 
*@author Devonmusa
*@date 2017年4月1日
*/
public class KafkaProducerConfig {
	private String topic;
	private String  zookeeperUrl;
	
	private Properties properties = new Properties();
	
	private static final KafkaProducerConfig producerConfig = new KafkaProducerConfig();
	 
	private KafkaProducerConfig(){
		
	}
	
	public static KafkaProducerConfig getInstance(){
			return producerConfig;
	}
	
	public String getTopic() {
		return topic;
	}

	public String getZookeeperUrl() {
		return zookeeperUrl;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setZookeeperUrl(String zookeeperUrl) {
		this.zookeeperUrl = zookeeperUrl;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void addProperty(String key,String value){
		properties.setProperty(key, value);
	}
	
}
