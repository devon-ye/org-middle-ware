package org.kafka.consumer.common;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.kafka.common.MessageHeader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年6月13日
 */
public class ReceiveDataThread extends Thread {
	private final Logger log = LoggerFactory.getLogger(ReceiveDataThread.class);

	private KafkaConsumer<MessageHeader, byte[]> kafkaConsumer;

	private ConsumerRecords<MessageHeader, byte[]> consumerRecords;

	private volatile boolean isRunning = false;
	

	public ReceiveDataThread(AbstrctReceiveWrapper receiveWrapper) {
		kafkaConsumer = receiveWrapper.getKafkaConsumer();
	}

	public void run() {
		isRunning = true;
		while (isRunning) {
			consumerRecords = kafkaConsumer.poll(100);
			for (ConsumerRecord<MessageHeader, byte[]> consumerRecord : consumerRecords) {
				MessageHeader header = consumerRecord.key();
				byte[] value = consumerRecord.value();

				log.info("consumer MessageHeader=" + header + ", value=" + value);
			}
		}

	}

	public boolean dispose() {
		return isRunning = false;
	}
}
