package org.kafka.consumer.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.kafka.common.ConsumerRebalanceListenerImpl;
import org.kafka.common.IMessageListener;
import org.kafka.common.MessageHeader;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.consumer.common.AbstrctReceiveWrapper;
import org.kafka.util.KafkaConstant;
import org.kafka.util.KafkaMetaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年5月13日
 */
public class KafkaAsyncReceiverWrapper extends AbstrctReceiveWrapper {

	private final static Logger LOG = LoggerFactory.getLogger(KafkaAsyncReceiverWrapper.class);

	private ConsumerRebalanceListenerImpl consumerRebalanceListenerImpl;

	private KafkaConsumer<MessageHeader, byte[]> kafkaConsumer;
	
	private ConsumerRecords<MessageHeader, byte[]> consumerRecords;

	private List<PartitionInfo> partitionInfos;

	private KafkaConsumerConfig consumerConfig;

	private int reTryCount = 0;

	private final static int MAX_RECONNET_TIMES = 5;

	public KafkaAsyncReceiverWrapper(KafkaConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
		this.consumerRebalanceListenerImpl = consumerRebalanceListenerImpl;
		init();
	}
	@Override
	public synchronized void receive(IMessageListener imessageListener) {
		try{
			consumerRecords = kafkaConsumer.poll(100);
			for (ConsumerRecord<MessageHeader, byte[]> consumerRecord : consumerRecords) {
				MessageHeader header = consumerRecord.key();
				byte[] value = consumerRecord.value();
				imessageListener.onMessage(header, value);
			}
		}catch(Exception e) {
			LOG.error("KafakConsumer  error !!! prefering to try reConnect!!!  Exception:" + e);
			init();
		}
	}

	public void close() {
		if (kafkaConsumer != null) {
			kafkaConsumer.close();
			kafkaConsumer = null;
		}
	}

	public KafkaConsumer<MessageHeader, byte[]> getKafkaConsumer() {
		return kafkaConsumer;
	}

	private void init() {
		try {
			connect();
		} catch (Exception e) {

			reTryConnect();
		} finally {
			subscriptionTopics();

		}

	}

	private void connect() throws Exception {
		if (kafkaConsumer != null) {
			kafkaConsumer = null;
		}

		String brokers = KafkaMetaUtils.getBrokers(consumerConfig.getZookeeperUrl());
		consumerConfig.put(KafkaConstant.BROKER_SERVERS, brokers);
		try {
			kafkaConsumer = new KafkaConsumer<>(consumerConfig.getConsumerConfig());

		} catch (Exception e) {
			LOG.error("KafakConsumer init failed! tring reConnect, Exception:" + e);
			reTryConnect();
		}

	}

	private void reTryConnect() {
		reTryCount++;
		if (kafkaConsumer != null) {
			kafkaConsumer = null;
		}
		if (reTryCount <= MAX_RECONNET_TIMES) {
			try {
				Thread.sleep(3000 * reTryCount);
				connect();
			} catch (Exception e) {
				LOG.error("Kafka Consumer reConncet  " + reTryCount + "  failed!!!");
				reTryConnect();
			}
		} else {
			LOG.error("Kafka Consumer reConncet  " + reTryCount + "  failed!!!");
		}

	}

	private void subscriptionTopics() {
		String topic = consumerConfig.getTopic();
		Collection<String> topics = new ArrayList<>();
		topics.add(topic);
		kafkaConsumer.subscribe(topics);
	//	kafkaConsumer.subscribe(topics, consumerRebalanceListenerImpl);

	}

	public List<PartitionInfo> getPartitionInfos() {
		partitionInfos = kafkaConsumer.partitionsFor(consumerConfig.getTopic());
		return partitionInfos;
	}

	public KafkaAsyncReceiverWrapper clone() {
		Object object = null;
		try {
			object = super.clone();
			return (KafkaAsyncReceiverWrapper) object;
		} catch (Exception e) {
			LOG.error("KafkaAsyncReceiverWrapper clone failed! Exception: " + e);
		}
		return null;
	}

}
