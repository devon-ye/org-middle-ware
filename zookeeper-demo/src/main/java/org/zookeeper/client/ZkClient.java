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
	private ZooKeeper zooKeeper;
	private final int  SESSION_TIME_OUT = 1000 * 10;
	private  ZkDefaultWatcher zkDefaultWatcher;
	
	public ZkClient(String zookeeperUrl){
		this.zookeeperUrl = zookeeperUrl;
		
	}
	public void init() throws Exception {
		zkDefaultWatcher = new ZkDefaultWatcher();
		zkConnect();
	}
	public void createNode(String path) throws KeeperException, InterruptedException {
		//zooKeeper.create(path, path.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		zooKeeper.create(path, "this is new create data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	public void deleteNode(String path) {
		try {
			zooKeeper.delete(path, -1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	
	public void setWatcherForParentNode(String path) {
		try {
			zooKeeper.exists(path, true);
			
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setWatcherForChildsNode(String path) {
		try {
			zooKeeper.getChildren(path, true);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void zkConnect( ) throws Exception{
		if(zooKeeper != null){
			zooKeeper.close();
		}
		zooKeeper = null;
		
		zooKeeper = new ZooKeeper(zookeeperUrl, SESSION_TIME_OUT, zkDefaultWatcher);
	}	
}
