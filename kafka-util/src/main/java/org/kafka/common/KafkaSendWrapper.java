package org.kafka.common;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kafka.producer.common.ProducerRecordWrapper;
import org.kafka.util.KafkaConstant;
import org.kafka.util.KafkaMetaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@see
*@author  Devonmusa
*@date 2017年4月6日
*/
public class KafkaSendWrapper {
	private static final Logger log = LoggerFactory.getLogger(KafkaSendWrapper.class);
	private static final int CLOSE_WAIT_TIMEMS = 10;
	private KafkaProducer producer;
	private Properties props;
	private ProducerRecord producerRecord;
	private KafkaProducerConfig producerConfig;
	//private ProducerRecordWrapper producerRecordWrapper;
	
	public KafkaSendWrapper(KafkaProducerConfig producerConfig){
		this.producerConfig = producerConfig;
		 try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void send(ProducerRecordWrapper producerRecordWrapper) throws Exception{
		producerRecord = producerRecordWrapper.getProducerRecord();
		Future future = producer.send(producerRecord);
	}
	
	public void close(){
		producer.close(CLOSE_WAIT_TIMEMS, TimeUnit.SECONDS);
	}
	
	private void init() throws Exception{
		if(null == producer){
			this.connect();
		}
	}
	
	private void connect() throws Exception{
		String topic = producerConfig.getTopic();
		if(null == topic || topic.length() == 0){
			log.error("Producer init faield ,topic is null");
			throw new Exception("Kafka Producer init failed,this topic is null!!!");
		}
		ProducerRecordWrapper.se
		String zookeeperUrl = producerConfig.getZookeeperUrl();
		if(null == zookeeperUrl){
			log.error("Producer init faield ,topic is null");
			throw new Exception("Kafka Producer init failed,this topic is null!!!");
		}
		String brokers = KafkaMetaUtils.getBrokers(zookeeperUrl);
		props = producerConfig.getProperties();
		props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, brokers);
		props.put(KafkaConstant.TOPIC_NAME, topic);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//props.put(KafkaConstant, kafka.serializer.StringDecoder);
		producer = new KafkaProducer<MessageHeader,byte[]>(props);
	}
}
