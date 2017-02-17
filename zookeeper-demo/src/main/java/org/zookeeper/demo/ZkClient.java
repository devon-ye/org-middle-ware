package org.zookeeper.demo;

import org.apache.zookeeper.ZooKeeper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.zookeeper.demo.watcher.ZkDefaultWatcher;

/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月18日 上午2:56:04
*
*/
public class ZkClient {
	private String zookeeperUrl;
	private ZooKeeper zooKeeper;
	private final int  SESSION_TIME_OUT = 1000 * 10;
	private  ZkDefaultWatcher zkDefaultWatcher;
	
	public ZkClient(String zookeeperUrl){
		this.zookeeperUrl = zookeeperUrl;
	}
	public void init() {
		
	}
	public void createNode() {
		
	}
	
	public void deleteNode() {
		
	}
	
	private void zkConnect( ) throws Exception{
		if(zooKeeper != null){
			zooKeeper.close();
		}
		zooKeeper = null;
		
		zooKeeper = new ZooKeeper(zookeeperUrl, SESSION_TIME_OUT, zkDefaultWatcher);
	}
	

	
}
