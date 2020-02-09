package org.yutil.kafka.proxy;

import org.yutil.kafka.common.IMessageListener;

/**
*
*@author Devonmusa
*@date   2017年4月27日
*/
public abstract class AbstrctReceiveStrategy {
	
	public abstract void receive();
	
	public abstract void close();

	public abstract void receive(IMessageListener imessageListener);
	
}
