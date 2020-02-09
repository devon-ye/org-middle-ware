package org.mw.zookeeper.proxy;



/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月23日 上午12:47:14
*
*/
public class ZkProxy implements IZookeeper{
	
	private ZookeeperImpl zookeeperImpl;
	
	public ZkProxy(String zooKeeperUrl) {
		zookeeperImpl  = ZookeeperImpl.getInstance();
		zookeeperImpl.setZooKeeperUrl(zooKeeperUrl);
	}

	
	public  void init() {
		 zookeeperImpl.init();
	}

	public void reTryConnect() {
		
	}

	public void createNodeOfPersistent(String path) {
		zookeeperImpl.createNodeOfPersistent(path);
	}
	
	public void createNodeOfPersistentSequential(String path) {
		zookeeperImpl.createNodeOfPersistentSequential(path);
	}
	
	public void createNodeOfEphemeral(String path) {
		zookeeperImpl.createNodeOfEphemeral(path);
	}
	
	public void createNodeOfEphemeralSequential(String path) {
		zookeeperImpl.createNodeOfPersistentSequential(path);
	}
	
	public boolean close() {
		return zookeeperImpl.close();
	}
}
