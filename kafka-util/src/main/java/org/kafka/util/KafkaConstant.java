package org.kafka.util;
/**
*@see
*@author  Devonmusa
*@date 2017年4月6日
*/
public class KafkaConstant {
	
	/**
	 * producer config
	 */
	public static final String METADATA_BROKER_LIST="metadata.broker.list";
	public static final String REQUEST_REQUIRED_ACKS="acks";
	public static final String TOPIC_NAME="topic.name";
	public static final String PARTITIONER_CLASS="partitioner.class";
	public static final String PRODUCER_TYPE="producer.type";
	public static final String MESSAGE_SEND_MAX_RETRIES="message.send.max.retries";
	public static final String KEY_SERIALIZER_CLASS="key.serializer.class";
	
	/**
	 * consumer config
	 */
	public static final String ZOOKEEPER_CONNECT="zookeeper.connect";
	public static final String ZOOKEEPER_CONNECT_TIMEOUT="zookeeper.connect.timeout";
	public static final String CONSUMER_RECOVER_OFFSET="consumer.recover.offset";
	public static final String GROUP_ID="group.id";
	public static final String AUTO_COMMIT_INTERVAL_MS = "auto.commit.interval.ms";
	public static final String CONSUMER_THREAD_NUM = "consumer.thread.num";
	public static final String HEADER_DECODE_CLASS = "header.decode.class";
}
