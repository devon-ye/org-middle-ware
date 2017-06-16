package org.kafka.proxy;

import org.kafka.common.IMessageListener;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public abstract class KafkaReceiveStrategegy {
	
	public abstract void receive();
	
	public abstract void close();

	public abstract void receive(IMessageListener imessageListener);
	
}
