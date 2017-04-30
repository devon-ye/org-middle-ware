package org.kafka.producer.common;

import java.util.concurrent.LinkedBlockingQueue;
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
	private LinkedBlockingQueue<ProducerRecordWrapper> linkedBlockingQueue;
	private ProducerRecordWrapper producerRecordWrapper;
	private KafkaSendWrapper kafkaSendWrapper;
	private int queueSize;
	private volatile boolean isRunning = false;

	public SendDataThread(LinkedTransferQueue<ProducerRecordWrapper> linkedTransferQueue, KafkaSendWrapper kafkaSendWrapper) {
		this.linkedTransferQueue = linkedTransferQueue;
		this.isRunning = true;
		this.kafkaSendWrapper = kafkaSendWrapper;
	}
	
	public SendDataThread(LinkedBlockingQueue<ProducerRecordWrapper> linkedBlockingQueue, KafkaSendWrapper kafkaSendWrapper) {
		this.linkedBlockingQueue = linkedBlockingQueue;
		this.isRunning = true;
		this.kafkaSendWrapper = kafkaSendWrapper;
	}
	
//	@Override
//	public void run()  {
//		while (isRunning) {
//			queueSize = linkedTransferQueue.size();
//			try {
//				producerRecordWrapper = linkedTransferQueue.take();
//				//producerRecordWrapper = linkedTransferQueue.poll(100,TimeUnit.MILLISECONDS);
//				LOG.info("linkedTransferQueue="+linkedTransferQueue+"queueSize=" + queueSize);
//			} catch (InterruptedException e) {
//				LOG.error("currntThreadName=" + Thread.currentThread() + ",queueSize=" + queueSize + ", isRunning=" + isRunning);
//			}
//			if (producerRecordWrapper != null) {
//				send();
//			} 
//		}
//	}
	
	@Override
	public void run()  {
		while (isRunning) {
			queueSize = linkedBlockingQueue.size();
			try {
				producerRecordWrapper = linkedBlockingQueue.take();
				//producerRecordWrapper = linkedBlockingQueue.poll(100,TimeUnit.MILLISECONDS);
				//LOG.info("linkedBlockingQueue="+linkedBlockingQueue+"queueSize=" + queueSize);
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
