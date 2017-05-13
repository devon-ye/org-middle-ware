package org.kafka.consumer.common;
/**
*
*@author Devonmusa
*@date   2017年5月7日
*/
public abstract class KafkaAbstractConsumer {

	public abstract void init();
	
	public abstract void connect();
	
	public abstract void reConnect();
	
	public abstract void close();
	
	
	
}
