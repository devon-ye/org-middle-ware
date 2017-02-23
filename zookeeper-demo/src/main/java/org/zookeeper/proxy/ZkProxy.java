package org.zookeeper.proxy;

import org.apache.zookeeper.ZooKeeper;

/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月23日 上午12:47:14
*
*/
public class ZkProxy implements IZookeeper{
	
	//private IZookeeper zookeeper = null;
	private final String zooKeeperUrl;
	private  ZooKeeper zooKeeper;
	ZookeeperImpl zookeeperImpl;
	
	public ZkProxy(String zooKeeperUrl) {
		this.zooKeeperUrl = zooKeeperUrl;
		zookeeperImpl  = ZookeeperImpl.getInstance();
		zookeeperImpl.setZooKeeperUrl(zooKeeperUrl);
	}

	
	public  void init() {
		 zookeeperImpl.init();
	}

	@Override
	public void reTryConnect() {
		
	}

	@Override
	public void createNodeOfPersistent(String path) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
	}
}
