package org.kafka.common;


import java.util.Properties;

import org.kafka.util.KafkaConstant;


/**
 *
 * @author Devonmusa
 * @date 2017年5月21日
 */
public abstract class AbstractConfig {
	private  Properties properties = new Properties();
	
	private String topic;

	private String zookeeperUrl;

	public String getTopic() {
		return topic;
	}

	public String getZookeeperUrl() {
		return zookeeperUrl;
	}

	public void setTopic(String topic) {
		properties.put(KafkaConstant.TOPIC_NAME, topic);
	}
}
