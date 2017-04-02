package org.kafka.sender;

import org.kafka.async.common.MessageHeader;

/**
*@see
*@author  Devonmusa
*@date 2017年4月2日
*/
public abstract class KafkaSenderStrategy {
	 
	public abstract void send(MessageHeader header,byte[] data);
	
	public abstract void close();
}
