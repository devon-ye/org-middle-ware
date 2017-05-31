package org.kafka.common;

import java.util.Collection;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

/**
 *
 * @author Devonmusa
 * @date 2017年6月1日
 */
public class ConsumerRebalanceListenerImpl implements ConsumerRebalanceListener {

	@Override
	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		// TODO Auto-generated method stub

	}

}
