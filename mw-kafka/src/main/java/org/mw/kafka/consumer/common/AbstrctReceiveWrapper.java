package org.mw.kafka.consumer.common;

import java.util.Collection;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.mw.kafka.common.IMessageListener;
import org.mw.kafka.common.MessageHeader;

/**
*
*@author Devonmusa
*@date   2017年6月13日
*/
public abstract class AbstrctReceiveWrapper implements Cloneable{

  public abstract void receive(IMessageListener imessageListener) ;
  
  public abstract KafkaConsumer<MessageHeader, byte[]> getKafkaConsumer();
  
  public abstract Collection<String> getTopics();
  
}
