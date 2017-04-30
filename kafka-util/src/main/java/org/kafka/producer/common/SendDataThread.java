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

	private Logger LOG = LoggerFactory.getLogger(SendDataThread.class);

	private LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private KafkaSendWrapper kafkaSendWrapper;
	private int queueSize;
	private volatile boolean isRunning = false;

	public SendDataThread(LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue, KafkaSendWrapper kafkaSendWrapper) {
		this.linkedTransferQueue = linkedTransferQueue;
		this.isRunning = true;
		this.kafkaSendWrapper = kafkaSendWrapper;
	}

	@Override
	public void run()  {
		while (isRunning) {
			queueSize = linkedTransferQueue.size();
			try {
				producerRecordWrapper = linkedTransferQueue.poll(100,TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (producerRecordWrapper != null) {
			//	LOG.info("currntThreadName=" + Thread.currentThread() + ",queueSize=" + queueSize + ", isRunning=" + isRunning);
				send();
			} 
//			else {
//				LOG.info("currntThreadName=" + Thread.currentThread() + ",linkedTransferQueue=" + linkedTransferQueue + "producerRecordWrapper=" + producerRecordWrapper);
//			}
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
