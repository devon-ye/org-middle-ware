package org.mw.kafka.proxy;

import org.mw.kafka.common.MessageHeader;

/**
*@see
*@author  Devonmusa
*@date 2017年4月2日
*/
public abstract class AbstractSenderStrategy {
	 
	public abstract void send(MessageHeader header, byte[] value);
	
	public abstract void close();
}
