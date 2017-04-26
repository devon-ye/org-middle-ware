package org.kafka.proxy;

import org.kafka.common.MessageHeader;

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
