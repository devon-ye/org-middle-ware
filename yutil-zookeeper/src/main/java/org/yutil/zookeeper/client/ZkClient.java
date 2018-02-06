package org.yutil.zookeeper.client;

import org.apache.zookeeper.KeeperException;
import org.yutil.zookeeper.proxy.ZkProxy;

/**
 * Describetion:
 * 
 * @author Devonmusa
 * @version 2017年2月18日 上午2:56:04
 *
 */
public class ZkClient {
	private String zooKeeperUrl;
	private ZkProxy zkProxy;

	public ZkClient(String zooKeeperUrl) {
		this.zooKeeperUrl = zooKeeperUrl;
	}

	public void init() throws Exception {
		zkProxy = new ZkProxy(zooKeeperUrl);
		zkProxy.init();
	}

	public void createNode(String path) throws KeeperException, InterruptedException {
		zkProxy.createNodeOfPersistent(path);
	}

	public void deleteNode(String path) {
		zkProxy.createNodeOfPersistent(path);
	}

	public void setWatcherForParentNode(String path) {

	}

	public void setWatcherForChildsNode(String path) {

	}

}
