package org.kafka.util;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.kafka.codec.ZkStringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import kafka.utils.Json;
import kafka.utils.ZKStringSerializer;
import scala.Option;
import scala.util.parsing.json.JSONArray;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月8日
 */
public class KafkaMetaUtils {
	private static final Logger logger = LoggerFactory.getLogger(KafkaMetaUtils.class);
	private static ZkClient zkClient;

	public static String getBrokers(String zookeeperUrl) {
		StringBuffer sockets = new StringBuffer();
		zkClient = new ZkClient(zookeeperUrl, 5000);
		zkClient.setZkSerializer(new ZkStringSerializer());
		List<String> brokerIds = zkClient.getChildren("/brokers/ids");
		String path = "/brokers/ids";
		for (String brokerId : brokerIds) {
			boolean isExists = zkClient.exists(path+"/" + brokerId);
			if (isExists) {
				String brokerInfo = zkClient.readData(path + "/" + brokerId);
				JSONObject jsonObject = JSON.parseObject(brokerInfo);
				String host = jsonObject.getString("host");
				String port = jsonObject.getString("port");
				if (brokerId != brokerIds.get(brokerIds.size() - 1)) {
					sockets.append(host + ":").append(port + ",");
				} else {
					sockets.append(host + ":").append(port);
				}

			}

		}
		return sockets.toString();
	}
}