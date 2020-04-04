package org.mw.zookeeper.watcher;


import java.util.concurrent.LinkedBlockingQueue;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Describetion:
 * 
 * @author Devonmusa
 * @version 2017年2月18日 上午3:22:01
 *
 */
public class ZkDefaultWatcher implements Watcher {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkDefaultWatcher.class);

	private final LinkedBlockingQueue<WatchedEvent> eventsQueue = new LinkedBlockingQueue<WatchedEvent>();

	public void process(WatchedEvent event) {
		try {
			eventsQueue.put(event);
		} catch (InterruptedException e) {
			LOGGER.error("e:",e);
		}
		if (KeeperState.SyncConnected == event.getState()) {
			if (EventType.None == event.getType() && null == event.getType()) {
				
			} else if (EventType.NodeCreated == event.getType()) {
				LOGGER.info("节点（" + event.getPath() + "）被创建");
			} else if (EventType.NodeDeleted == event.getType()) {
				LOGGER.info("节点（" + event.getPath() + "）被删除");
			} else if (EventType.NodeDataChanged == event.getType()) {
				LOGGER.info("节点（" + event.getPath() + "）数据改变");
			} else if (EventType.NodeChildrenChanged == event.getType()) {
				LOGGER.info("子节点（" + event.getPath() + "）改变");
			}
		}
		eventsQueue.poll();

	}

}
