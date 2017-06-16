package org.kafka.consumer.common;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.kafka.common.IMessageListener;
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

	private AbstrctReceiveWrapper receiveWrapper;

	private IMessageListener imessageListener;

	private volatile boolean isRunning = false;
	

	public ReceiveDataThread(AbstrctReceiveWrapper receiveWrapper, IMessageListener imessageListener) {
		this.receiveWrapper = receiveWrapper;
		this.imessageListener = imessageListener;
	}

	public void run() {
		isRunning = true;
		while (isRunning) {
			receiveWrapper.receive(imessageListener);
		}

	}

	public boolean dispose() {
		return isRunning = false;
	}
}
