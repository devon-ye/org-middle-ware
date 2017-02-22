package org.zookeeper.proxy;
/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月23日 上午12:47:14
*
*/
public class ZkProxy implements IZookeeper{
	
	private IZookeeper zookeeper = null;
	private final String zookeeperUrl;
	
	public ZkProxy(String zookeeperUrl) {
		this.zookeeperUrl = zookeeperUrl;
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void connect() {
		// TODO Auto-generated method stub
		
	}

	public void reTryConnect() {
		// TODO Auto-generated method stub
		
	}

	public void createNodeOfPersistent() {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
