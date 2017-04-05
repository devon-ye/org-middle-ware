package org.kafka.common;

import java.util.Properties;

/**
*@see 
*@author Devonmusa
*@date 2017年4月1日
*/
public class KafkaProducerConfig {
	private String topic;
	private Properties props;
	private final long  DEFAULT_SESSION_TIMEOUT = 3 * 1000;
	private String  zookeeperUrl;
	
	public KafkaProducerConfig(Properties props){
		this.props = props;	
	}
	
	private void  doConfig(){
		
	}
	
	public Properties getConfig(){
		return props;
	}
	
}
