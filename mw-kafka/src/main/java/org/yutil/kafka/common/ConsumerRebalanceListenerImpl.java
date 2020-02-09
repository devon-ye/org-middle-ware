package org.yutil.kafka.common;

import java.util.Collection;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年6月1日
 */
public class ConsumerRebalanceListenerImpl implements ConsumerRebalanceListener {
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerRebalanceListenerImpl.class); 
	
	private ConsumerOffsetCommitedThread consumerOffsetCommitedThread;
	private List<String>  topics;
	
	
	
	public ConsumerRebalanceListenerImpl(ConsumerOffsetCommitedThread consumerOffsetCommitedThread) {
		this.consumerOffsetCommitedThread = consumerOffsetCommitedThread;
	}
	
	@Override
	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		
		consumerOffsetCommitedThread.commitedOffset(partitions);
		LOG.info("partitions = " + partitions);
		

	}

	@Override
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		
		consumerOffsetCommitedThread.recoverOffset(partitions);

	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	
	
	

}
