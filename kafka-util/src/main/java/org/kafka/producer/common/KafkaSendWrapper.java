package org.kafka.producer.common;

/**
 *
 * @author Devonmusa
 * @date 2017年4月6日
 */
public abstract class KafkaSendWrapper implements Cloneable {

	public abstract void send(ProducerRecordWrapper producerRecordWrapper);
}
