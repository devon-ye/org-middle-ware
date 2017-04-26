package org.kafka.proxy;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public abstract class KafkaReceiveStrategegy {
	/**
	*@function:
	*
	*/
	public abstract void receive();
	
	public abstract void close();
}
