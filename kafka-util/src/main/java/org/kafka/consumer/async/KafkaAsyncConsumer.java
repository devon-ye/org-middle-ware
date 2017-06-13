package org.kafka.consumer.async;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.PartitionInfo;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.consumer.common.AbstrctReceiveWrapper;
import org.kafka.consumer.common.ReceiveDataThread;
import org.kafka.proxy.KafkaReceiveStrategegy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月27日
 */
public class KafkaAsyncConsumer extends KafkaReceiveStrategegy {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncConsumer.class);

	private KafkaAsyncReceiverWrapper kafkaAsyncReceiverWrapper;
	private List<PartitionInfo> partitionInfos;
	
	private Map<Integer,ReceiveDataThread> partitionRreceiveThreadMap = new HashMap<>();
	

	public KafkaAsyncConsumer(KafkaConsumerConfig conumerConfig) {
		kafkaAsyncReceiverWrapper = new KafkaAsyncReceiverWrapper(conumerConfig);
		partitionInfos = kafkaAsyncReceiverWrapper.getPartitionInfos();
	}

	@Override
	public void receive() {
		for (PartitionInfo partitionInfo : partitionInfos) {
			int patition = partitionInfo.partition();
			ReceiveDataThread receiveThread= partitionRreceiveThreadMap.get(patition);
			if(receiveThread == null) {
				AbstrctReceiveWrapper receiveWrapper = kafkaAsyncReceiverWrapper.clone();
				receiveThread  = new ReceiveDataThread(receiveWrapper);
				receiveThread.setName("receiveThread-" + patition);
				receiveThread.start();
			}
		}
		LOG.info("Consumer start recevier...");
		kafkaAsyncReceiverWrapper.receive();

	}

	@Override
	public void close() {
		
		LOG.info("Consumer finished closed...");
	}

}
