package org.mw.kafka.producer.common;

/**
 *
 * @author Devonmusa
 * @date 2017年4月6日
 */
public abstract class AbstractSendWrapper implements Cloneable {

	public abstract void send(ProducerRecordWrapper producerRecordWrapper);
}
