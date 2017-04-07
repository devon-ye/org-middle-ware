package org.kafka.util;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@see
*@author  Devonmusa
*@date 2017年4月8日
*/
public class KafkaMetaUtil {
	private static final Logger logger = LoggerFactory.getLogger(KafkaMetaUtil.class); 
	private static ZkClient zkClient;
	public static String getBrokers(String zookeeperUrl){
		zkClient = new ZkClient(zookeeperUrl);
		
		return null;
	}
}
