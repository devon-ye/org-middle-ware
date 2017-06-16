package org.kafka.consumer.common;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.kafka.common.IMessageListener;
import org.kafka.common.MessageHeader;

/**
*
*@author Devonmusa
*@date   2017年6月13日
*/
public abstract class AbstrctReceiveWrapper implements Cloneable{

  public abstract void receive(IMessageListener imessageListener) ;
  
  public abstract KafkaConsumer<MessageHeader, byte[]> getKafkaConsumer();
  
}
