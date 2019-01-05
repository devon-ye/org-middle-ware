package org.yutil.kafka.consumer.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.yutil.kafka.util.KafkaConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaConsumerConfig {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerConfig.class);

	private long offSet;

	private String topic;

	private int partitionId;
	
	private String zookeeperUrl;

	public String getZookeeperUrl() {
		return zookeeperUrl;
	}

	public void setZookeeperUrl(String zookeeperUrl) {
		this.zookeeperUrl = zookeeperUrl;
	}

	private static KafkaConsumerConfig instance = new KafkaConsumerConfig();

	private  Properties properties = new Properties();

	private KafkaConsumerConfig() {
		init();
	}

	public static KafkaConsumerConfig getInstance() {
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

	public void setOffSet(long offSet) {
		properties.put(KafkaConstant.CONSUMER_RECOVER_OFFSET, offSet);
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setPartitionId(int partitionId) {
		properties.put(KafkaConstant.ZOOKEEPER_CONNECT, partitionId);
	}
	/**
	 * this properties will replace defalut propertie
	 * @param properties
	 */
	public void setProperties(Properties properties) {
		this.properties.putAll(properties);
	}
	
	public Properties getConsumerConfig( ) {
		return properties;
	}
	
	public void put(String key, String value) throws Exception {
		checkProperty(key, value);
		properties.put(key, value);
	}

	private void init() {
		getConsumerDefaultConfig();
	}

	private Properties getConsumerDefaultConfig() {

		InputStream inputStream = null;
		String configFilePath = System.getProperty("user.dir") + "/config/consumer.properties";
		File file = new File(configFilePath);
		if (!file.exists()) {
			return properties;
		}
		try {
			inputStream = new FileInputStream(file);

			properties.load(inputStream);

		} catch (FileNotFoundException e) {
			LOG.error("load consumer default config file Exception:" + e);
		} catch (IOException e) {
			LOG.error("load consumer default config file Exception:" + e);
		}

		return properties;
	}

	private void checkProperty(String key, String value) throws Exception{
		if (key == null || value == null) {
		//	new NullPointerException("key=" + key + " ,value=" + value + " is null");
		}
		return;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KafkaConsumerConfig [offSet=");
		builder.append(offSet);
		builder.append(", topic=");
		builder.append(topic);
		builder.append(", partitionId=");
		builder.append(partitionId);
		builder.append(", properties=");
		builder.append(properties);
		builder.append("]");
		return builder.toString();
	}
}
