package org.kafka.consumer.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.kafka.common.MessageHeader;
import org.kafka.consumer.common.KafkaConsumerConfig;
import org.kafka.util.KafkaConstant;
import org.kafka.util.KafkaMetaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Devonmusa
 * @date 2017年5月13日
 */
public class KafkaAsyncReceiverWrapper implements Cloneable {

	private final static Logger LOG = LoggerFactory.getLogger(KafkaAsyncReceiverWrapper.class);

	private KafkaAsyncRecevierThread kafkaAsyncRecevierThread;

	private KafkaConsumer<MessageHeader, byte[]> kafkaConsumer;

	private List<PartitionInfo> partitionInfos;

	private KafkaConsumerConfig consumerConfig;
	private String topic = null;

	private int reTryCount = 0;

	private final static int MAX_RECONNET_TIMES = 5;

	public KafkaAsyncReceiverWrapper(KafkaConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
		init();
	}

	protected void receive() {
		kafkaAsyncRecevierThread = new KafkaAsyncRecevierThread(this);
		kafkaAsyncRecevierThread.start();
	}

	public void close() {
		if (kafkaConsumer != null) {
			//kafkaConsumer.close();
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
			getPartitionInfos();
		}

	}

	private void connect() throws Exception {
		if (kafkaConsumer != null) {
			kafkaConsumer = null;
		}

		String brokers = KafkaMetaUtils.getBrokers(consumerConfig.getZookeeperUrl());
		// consumerConfig.put(KafkaConstant.TOPIC_NAME,
		// consumerConfig.getTopic());
		consumerConfig.put(KafkaConstant.BROKER_SERVERS, brokers);
		try {
			kafkaConsumer = new KafkaConsumer<>(consumerConfig.getConsumerConfig());
			//kafkaConsumer = new KafkaConsumer<>(consumerConfig.getConsumerConfig(), new MessageHeaderEncode(), valueDeserializer)
		} catch (Exception e) {
			LOG.info("KafakConsumer init failed! tring reConnect, Exception:" + e);
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
				LOG.error("Kafka Consumer reConncet  " + reTryCount + "  failed");
				reTryConnect();
			}
		} else {
			LOG.error("Kafka Consumer reConncet  " + reTryCount + "  failed");
		}

	}
	private void subscriptionTopics() {
		String  topic = consumerConfig.getTopic();
		Collection<String> topics = new ArrayList<>();
		topics.add(topic);
		kafkaConsumer.subscribe(topics);
	}
	private List<PartitionInfo> getPartitionInfos() {
		partitionInfos = kafkaConsumer.partitionsFor(consumerConfig.getTopic());
		return partitionInfos;
	}
	
	public String getTopic() {
		return topic;
	}
}
