package org.kafka.producer.common;



import org.apache.kafka.clients.producer.ProducerRecord;
import org.kafka.common.MessageHeader;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月5日
 */
public class ProducerRecordWrapper {
	private byte[] value;

	private MessageHeader messageHeader;
	private String topic;

	private ProducerRecord<MessageHeader, byte[]> producerRecord;

	public ProducerRecordWrapper(MessageHeader messageHeader, byte[] value) throws Exception {
		this.messageHeader = messageHeader;
		this.value = value;
		getProducerRecord();
	}

	public ProducerRecord<MessageHeader, byte[]> getProducerRecord() throws Exception {
		if(topic == null){
			 throw new Exception("topic is null!!!");
		}
		producerRecord = new ProducerRecord<MessageHeader, byte[]>(topic, messageHeader, value);
		return producerRecord;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
