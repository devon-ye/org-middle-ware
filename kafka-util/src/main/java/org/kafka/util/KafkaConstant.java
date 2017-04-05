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
	public static final String metadata_broker_list="metadata.broker.list";
	public static final String request_required_acks="acks";
	public static final String topic_name="topic.name";
	public static final String partitioner_class="partitioner.class";
	public static final String producer_type="producer.type";
	public static final String message_send_max_retries="message.send.max.retries";
	public static final String key_serializer_class="key.serializer.class";
	
	/**
	 * consumer config
	 */
	public static final String zookeeper_connect="zookeeper.connect";
	public static final String zookeeper_connect_timeout="zookeeper.connect.timeout";
	public static final String consumer_recover_offset="consumer.recover.offset";
	public static final String group_id="group.id";
	public static final String auto_commit_interval_ms = "auto.commit.interval.ms";
	public static final String consumer_thread_num = "consumer.thread.num";
	public static final String header_decode_class = "header.decode.class";
}
