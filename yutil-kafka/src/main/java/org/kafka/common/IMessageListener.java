package org.kafka.common;



/**
*
*@author Devonmusa
*@date   2017年4月29日
*/
public interface IMessageListener {
	
	public void onMessage(MessageHeader header,byte[] data);
}
