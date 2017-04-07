package org.kafka.common;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kafka.async.common.MessageHeader;
import org.kafka.producer.common.ProducerRecordWrapper;
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
	}
	
	
	
	public void send(ProducerRecordWrapper producerRecordWrapper){
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
		String zookeeperUrl = producerConfig.getZookeeperUrl();
		if(null == zookeeperUrl){
			log.error("Producer init faield ,topic is null");
			throw new Exception("Kafka Producer init failed,this topic is null!!!");
		}
		props = producerConfig.getProperties();
		producer = new KafkaProducer<MessageHeader,byte[]>(props);
	}
	
	private void reTryConnect(){
		
	}
}
