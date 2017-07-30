package org.kafka.consumer.common;

import java.util.Arrays;
import java.util.Collection;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.kafka.common.ConsumerRebalanceListenerImpl;
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
	private ConsumerRebalanceListener consumerRebalanceListener;

	private AbstrctReceiveWrapper receiveWrapper;

	private IMessageListener imessageListener;

	private volatile boolean isRunning = false;

	public ReceiveDataThread(AbstrctReceiveWrapper receiveWrapper, IMessageListener imessageListener,ConsumerRebalanceListener consumerRebalanceListener) {
		this.receiveWrapper = receiveWrapper;
		this.imessageListener = imessageListener;
		this.consumerRebalanceListener = consumerRebalanceListener;
	}

	public void run() {
		kafkaConsumer.subscribe( receiveWrapper.getTopics(), consumerRebalanceListener);
		//kafkaConsumer.subscribe(Arrays.asList("topic"));
		isRunning = true;

		try {
			while (isRunning) {
				receiveWrapper.receive(imessageListener);
			}
		} catch (WakeupException e) {
			// Ignore exception if closing
			if (isRunning) {
				throw e;
			}
				
		} finally {
			kafkaConsumer.close();
		}

	}

	public boolean dispose() {

		kafkaConsumer.wakeup();
		return isRunning = false;
	}
}
