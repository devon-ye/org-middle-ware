package org.yutil.kafka.producer.common;

import java.util.concurrent.LinkedTransferQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年4月26日
 */
public class SendDataThread extends Thread {

	private Logger LOG = LoggerFactory.getLogger(SendDataThread.class);

	private LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private AbstractSendWrapper kafkaSendWrapper;
	private int queueSize;
	private volatile boolean isRunning = false;

	public SendDataThread(LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue, AbstractSendWrapper kafkaSendWrapper) {
		this.linkedTransferQueue = linkedTransferQueue;
		this.isRunning = true;
		this.kafkaSendWrapper = kafkaSendWrapper;
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				producerRecordWrapper = linkedTransferQueue.take();
				queueSize = linkedTransferQueue.size();
			} catch (InterruptedException e) {
				LOG.error("currntThreadName=" + Thread.currentThread() + ",queueSize=" + queueSize + ", isRunning=" + isRunning);
			}
			if (producerRecordWrapper != null) {
				send();
			}
		}
	}

	public void send() {
		try {
			kafkaSendWrapper.send(producerRecordWrapper);
		} catch (Exception e) {
			LOG.info("send Exception:" + e + "kafkaSendWrapper=" + kafkaSendWrapper);
		}
	}

	public void dispose() {
		isRunning = false;
	}

	public int getQueueSize() {
		return queueSize;
	}
}
