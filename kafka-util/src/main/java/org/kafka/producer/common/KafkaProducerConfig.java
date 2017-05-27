package org.kafka.producer.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.kafka.common.AbstractConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月1日
 */
public class KafkaProducerConfig {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerConfig.class);

	private String topic;
	private String zookeeperUrl;

	private Properties properties = new Properties();

	private static final KafkaProducerConfig producerConfig = new KafkaProducerConfig();

	private KafkaProducerConfig() {
		init();
	}

	private void init() {
		getProducerDefaultConfig();
	}

	public static KafkaProducerConfig getInstance() {
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
		this.properties.putAll(properties);
	}

	public void addProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	private Properties getProducerDefaultConfig() {

		InputStream inputStream = null;
		String configFilePath = System.getProperty("user.dir") + "/config/producer.properties";
		File file = new File(configFilePath);
		if (!file.exists()) {
			return properties;
		}
		try {
			inputStream = new FileInputStream(file);

			properties.load(inputStream);

		} catch (FileNotFoundException e) {
			LOG.error("load consumer default config FileNotFoundException:" + e);
		} catch (IOException e) {
			LOG.error("load consumer default config file IOException:" + e);
		}

		return properties;
	}
}
