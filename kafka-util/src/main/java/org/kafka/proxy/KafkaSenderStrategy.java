package org.kafka.proxy;

import org.kafka.common.MessageHeader;

/**
*@see
*@author  Devonmusa
*@date 2017年4月2日
*/
public abstract class KafkaSenderStrategy {
	 
	public abstract void send(MessageHeader header,byte[] value);
	
	public abstract void close();
}
