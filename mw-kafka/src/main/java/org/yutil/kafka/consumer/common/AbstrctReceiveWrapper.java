package org.yutil.kafka.consumer.common;

import java.util.Collection;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.yutil.kafka.common.IMessageListener;
import org.yutil.kafka.common.MessageHeader;

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
