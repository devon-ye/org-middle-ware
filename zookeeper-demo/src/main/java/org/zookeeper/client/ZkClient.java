package org.zookeeper.client;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.zookeeper.watcher.ZkDefaultWatcher;

/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月18日 上午2:56:04
*
*/
public class ZkClient {
	private String zookeeperUrl;
	private ZooKeeper zookeeper;
	private final int  SESSION_TIME_OUT = 1000 * 10;

	
	public ZkClient(String zookeeperUrl){
		this.zookeeperUrl = zookeeperUrl;
		
	}
	public void init() throws Exception {
		
	}
	public void createNode(String path) throws KeeperException, InterruptedException {
		
	}
	
	public void deleteNode(String path) {
		
	}
	
	public void setWatcherForParentNode(String path) {
		
	}
	
	public void setWatcherForChildsNode(String path) {
		
	}
	
}
