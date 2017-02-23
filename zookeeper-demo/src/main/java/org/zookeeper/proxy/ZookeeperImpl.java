package org.zookeeper.proxy;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zookeeper.watcher.ZkDefaultWatcher;

import ch.qos.logback.core.joran.action.NewRuleAction;

/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月23日 上午1:05:29
*
*/
public class ZookeeperImpl implements IZookeeper{
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperImpl.class);
	private final int  SESSION_TIME_OUT = 1000 * 10;
	private  ZkDefaultWatcher zkDefaultWatcher;
	private  ZooKeeper zooKeeper;
	private String zooKeeperUrl;
	
	private static final  ZookeeperImpl zookeeperImpl = new ZookeeperImpl();
	private  ZookeeperImpl(){};
	
	public static ZookeeperImpl  getInstance(){
		return zookeeperImpl;
	}

	public void init() {
		zkDefaultWatcher = new ZkDefaultWatcher();
		zkConnect();	
	}

	private void zkConnect() {
		if(zooKeeper != null){
			try {
				zooKeeper.close();
			} catch (InterruptedException e) {
				logger.error("Zookeeper  init  Exception");
			}finally{
				zooKeeper = null;
			}
		}
		
		
		try {
			zooKeeper = new ZooKeeper(zooKeeperUrl, SESSION_TIME_OUT, zkDefaultWatcher);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void reTryConnect() {
		// TODO Auto-generated method stub
		
	}



	public void close() {
		
		
	}

	
	public void createNodeOfPersistent(String path) {
		// TODO Auto-generated method stub
		
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
	
	public boolean deleteNode(String path) {
		try {
			zooKeeper.delete(path, -1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String getZooKeeperUrl() {
		return zooKeeperUrl;
	}

	public void setZooKeeperUrl(String zooKeeperUrl) {
		this.zooKeeperUrl = zooKeeperUrl;
	}
	
	
}
