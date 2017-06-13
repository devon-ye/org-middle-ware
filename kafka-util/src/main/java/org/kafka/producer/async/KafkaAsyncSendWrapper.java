package org.kafka.producer.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.kafka.common.MessageHeader;
import org.kafka.producer.common.KafkaProducerConfig;
import org.kafka.producer.common.AbstractSendWrapper;
import org.kafka.producer.common.ProducerRecordWrapper;
import org.kafka.util.KafkaConstant;
import org.kafka.util.KafkaMetaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月30日
 */
public class KafkaAsyncSendWrapper extends AbstractSendWrapper implements Cloneable {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaAsyncSendWrapper.class);

	private static final int CLOSE_WAIT_TIMEMS = 10;

	private ProducerRecord<MessageHeader, byte[]> producerRecord;
	private KafkaProducer<MessageHeader, byte[]> producer;
	private KafkaProducerConfig producerConfig;
	private List<PartitionInfo> partitionInfos = new ArrayList<>();
	private Future<RecordMetadata> sendMessagefuture;
	private RecordMetadata recordMetadata;
	private Properties props;
	private String topic;
	private int reTryCount = 0;

	public KafkaAsyncSendWrapper(KafkaProducerConfig producerConfig) {
		this.producerConfig = producerConfig;
		try {
			init();
		} catch (Exception e) {
			LOG.error(" init() failed!!! Exception:" + e + reTryCount);
		}
	}

	public KafkaAsyncSendWrapper clone() {
		Object object = null;
		try {
			object = super.clone();
			return (KafkaAsyncSendWrapper) object;
		} catch (CloneNotSupportedException e) {
			LOG.error(" clone() failed!!! Exception:" + e);
			return null;
		}

	}

	public void send(ProducerRecordWrapper producerRecordWrapper) {
		producerRecord = producerRecordWrapper.getProducerRecord();
		if (producerRecord == null) {
			return;
		}
		producerRecord.partition();
		try {
			sendMessagefuture = producer.send(producerRecord);
			producerRecordWrapper.setFuture(sendMessagefuture);
		} catch (Exception e) {
			LOG.error("Exception:" + e + ", sendMessagefuture =" + sendMessagefuture);
		}

	}

	public void close() {
		producer.close(CLOSE_WAIT_TIMEMS, TimeUnit.SECONDS);
	}

	private void init() throws Exception {

		try {
			if (null == producer) {
				this.connect();
			}
		} catch (Exception e) {
			LOG.error("kafka init failed " + 3000 * reTryCount + "s after, prepare to reConnect!");
			reConnect();
			LOG.error("kafka init reConnect:" + reTryCount + " failed end!!!");
		} finally {
			reTryCount = 0;
		}
	}

	private void reConnect() {
		try {
			reTryCount++;
			if (reTryCount > 5) {
				return;
			}
			Thread.sleep(3000 * reTryCount);
			connect();
		} catch (Exception e) {
			LOG.error("kafka init failed! trying to reConnect:" + reTryCount);
			reConnect();
		}
	}

	private void connect() throws Exception {
		this.topic = producerConfig.getTopic();
		if (null == topic || topic.length() == 0) {
			LOG.error("Producer init faield ,topic is null");
			throw new Exception("Kafka Producer init failed,this topic is null!!!");
		}

		String zookeeperUrl = producerConfig.getZookeeperUrl();
		if (null == zookeeperUrl || zookeeperUrl.length() == 0) {
			LOG.error("Producer init faield ,topic is null");
			throw new Exception("Kafka Producer init failed,this topic is null!!!");
		}
		String brokers = KafkaMetaUtils.getBrokers(zookeeperUrl);
		props = producerConfig.getProperties();
		props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, brokers);
		props.put(KafkaConstant.TOPIC_NAME, topic);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.kafka.codec.MessageHeaderEncode");
		props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		producer = new KafkaProducer<MessageHeader, byte[]>(props);
	}

	public List<PartitionInfo> getPartitionInfos() {
		if (producer != null) {
			partitionInfos = producer.partitionsFor(topic);
			return partitionInfos;
		} else {
			return partitionInfos;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KafkaSendWrapper [producerRecord=");
		builder.append(producerRecord);
		builder.append(", producer=");
		builder.append(producer);
		builder.append(", producerConfig=");
		builder.append(producerConfig);
		builder.append(", partitionInfos=");
		builder.append(partitionInfos);
		builder.append(", recordMetadata=");
		builder.append(recordMetadata);
		builder.append(", props=");
		builder.append(props);
		builder.append(", topic=");
		builder.append(topic);
		builder.append(", reTryCount=");
		builder.append(reTryCount);
		builder.append("]");
		return builder.toString();
	}
}
