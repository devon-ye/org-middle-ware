package org.yutil.kafka.consumer.async;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.PartitionInfo;
import org.yutil.kafka.consumer.common.KafkaConsumerConfig;
import org.yutil.kafka.common.ConsumerOffsetCommitedThread;
import org.yutil.kafka.common.ConsumerRebalanceListenerImpl;
import org.yutil.kafka.common.IMessageListener;
import org.yutil.kafka.consumer.common.AbstrctReceiveWrapper;
import org.yutil.kafka.consumer.common.ReceiveDataThread;
import org.yutil.kafka.proxy.AbstrctReceiveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaAsyncConsumer extends AbstrctReceiveStrategy {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncConsumer.class);

	private KafkaAsyncReceiverWrapper kafkaAsyncReceiverWrapper;
	
	private ConsumerOffsetCommitedThread consumerOffsetCommitedThread;
	
	private ConsumerRebalanceListener consumerRebalanceListener;
	
	private List<PartitionInfo> partitionInfos;
	
	private Map<Integer,ReceiveDataThread> partitionRreceiveThreadMap = new HashMap<>(16);
	

	public KafkaAsyncConsumer(KafkaConsumerConfig conumerConfig) {
		kafkaAsyncReceiverWrapper = new KafkaAsyncReceiverWrapper(conumerConfig);
		partitionInfos = kafkaAsyncReceiverWrapper.getPartitionInfos();
	}
	
	public void init() {
		
	}
	
	
	@Override
	public void receive(final  IMessageListener imessageListener) {
		consumerOffsetCommitedThread = new ConsumerOffsetCommitedThread();
		consumerRebalanceListener =new ConsumerRebalanceListenerImpl(consumerOffsetCommitedThread);
		
		for (PartitionInfo partitionInfo : partitionInfos) {
			int patition = partitionInfo.partition();
			ReceiveDataThread receiveThread= partitionRreceiveThreadMap.get(patition);
			if(receiveThread == null) {
				AbstrctReceiveWrapper receiveWrapper = kafkaAsyncReceiverWrapper.clone();
				receiveThread  = new ReceiveDataThread(receiveWrapper,imessageListener,consumerRebalanceListener);
				receiveThread.setName("receiveThread-" + patition);
				receiveThread.start();
				partitionRreceiveThreadMap.put(patition, receiveThread);
				LOG.info("Consumer  "+ receiveThread +" started recevier...");
			}
			
		}
		
	}

	@Override
	public void close() {
		
		LOG.info("Consumer finished closed...");
	}

	@Override
	public void receive() {
		// TODO Auto-generated method stub
		
	}

	

}
