package org.kafka.common;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kafka.producer.common.ProducerRecordWrapper;

/**
*@see
*@author  Devonmusa
*@date 2017年4月6日
*/
public class KafkaSendWrapper {
	private static final int CLOSE_WAIT_TIMEMS = 10;
	private KafkaProducer producer;
	private Properties properties;
	private ProducerRecord producerRecord;
	private KafkaProducerConfig producerConfig;
	//private ProducerRecordWrapper producerRecordWrapper;
	
	public KafkaSendWrapper(KafkaProducerConfig producerConfig){
		this.producerConfig = producerConfig;
		this.properties = producerConfig.getConfig();
	}
	public void init(){
		if(null == producer){
			this.connect();
		}
	}
	
	public void send(ProducerRecordWrapper producerRecordWrapper){
		producerRecord = producerRecordWrapper.getProducerRecord();
		Future future = producer.send(producerRecord);
	}
	
	public void close(){
		producer.close(CLOSE_WAIT_TIMEMS, TimeUnit.SECONDS);
	}
	
	private void connect(){
		producer = new KafkaProducer<>(properties);
	}
	
	private void reTryConnect(){
		
	}
}
