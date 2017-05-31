package org.kafka.consumer.common;
/**
*
*@author Devonmusa
*@date   2017年5月13日
*/
public abstract class KafkaAbstractRecevier {
	
	protected abstract void init();
	
	protected abstract void connect();
	
	protected abstract void reTryConnect();
	
	protected abstract void close();
	
	
}
