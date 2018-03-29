package org.yutil.zookeeper.proxy;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yutil.zookeeper.watcher.ZkDefaultWatcher;


/**
 * Describetion:
 * 
 * @author Devonmusa
 * @version 2017年2月23日 上午1:05:29
 *
 */
public class ZookeeperImpl implements IZookeeper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperImpl.class);
	private final int SESSION_TIME_OUT = 1000 * 10;
	private ZkDefaultWatcher zkDefaultWatcher;
	private ZooKeeper zooKeeper;
	private String zooKeeperUrl;

	private static final ZookeeperImpl zookeeperImpl = new ZookeeperImpl();

	private ZookeeperImpl() {};

	public static ZookeeperImpl getInstance() {
		return zookeeperImpl;
	}

	public void init() {
		zkDefaultWatcher = new ZkDefaultWatcher();	
		zkConnect();
	}

	private void zkConnect() {
		if (zooKeeper != null) {
			try {
				zooKeeper.close();
			} catch (InterruptedException e) {
				LOGGER.error("Zookeeper  init  Exception");
			} finally {
				zooKeeper = null;
			}
		}

		try {
			zooKeeper = new ZooKeeper(zooKeeperUrl, SESSION_TIME_OUT, zkDefaultWatcher);
		} catch (IOException e) {
			LOGGER.error("ZookeeperImpl.zkConnect()  Exception!!!");
		}

	}

	public void reTryConnect() {
		// TODO Auto-generated method stub

	}

	public boolean close() {
		if (zooKeeper != null) {
			try {
				zooKeeper.close();
			} catch (Exception e) {
				LOGGER.error("Zookeeper close fail !!!" +e);
			}
			zooKeeper = null;
			return true;
		}else{
			return false;
		}
	}

	public void createNodeOfPersistent(String path) {
		try {
			zooKeeper.create(path, "22".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			LOGGER.error("KeeperException" ,e);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException" + e);
		}
	}
	
	public void createNodeOfEphemeral(String path) {
		
		try {
			zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (KeeperException e) {
			LOGGER.error("KeeperException" + e);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException" + e);
		}
	}
	
	public void createNodeOfPersistentSequential(String path) {
		
		try {
			zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		} catch (KeeperException e) {
			LOGGER.error("KeeperException" + e);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException" + e);
		}
	}
	
	public void createNodeOfEphemeralSequential(String path) {

		try {
			zooKeeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		} catch (KeeperException e) {
			LOGGER.error("KeeperException" + e);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException" + e);
		}
	}
	
	public void setWatcherForParentNode(String path) {
		try {
			zooKeeper.exists(path, true);

		} catch (KeeperException e) {
			LOGGER.error("KeeperException" + e);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException" + e);
		}
	}

	public void setWatcherForChildsNode(String path) {
		try {
			zooKeeper.getChildren(path, true);
		} catch (KeeperException e) {
			LOGGER.error("KeeperException" + e);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException" + e);
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
