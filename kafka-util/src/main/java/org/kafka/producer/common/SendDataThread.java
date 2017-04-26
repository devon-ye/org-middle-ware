package org.kafka.producer.common;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月26日
 */
public class SendDataThread extends Thread {
	private Logger log = LoggerFactory.getLogger(SendDataThread.class);

	private LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private KafkaSendWrapper kafkaSendWrapper;

	public SendDataThread(LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue) {
		this.linkedTransferQueue = linkedTransferQueue;
	}

	public void init(KafkaSendWrapper kafkaSendWrapper) {
		this.kafkaSendWrapper = kafkaSendWrapper;
	}

	public void run() {
		try {
			producerRecordWrapper = linkedTransferQueue.poll(100, TimeUnit.MILLISECONDS);
			send();

		} catch (InterruptedException e) {
			log.info("run Exception:" + e);
		}
	}

	public void send() {
		try {
			kafkaSendWrapper.send(producerRecordWrapper);
		} catch (Exception e) {
			log.info("send Exception:" + e);
		}
	}

	public void dispose() {
		
	}
}
