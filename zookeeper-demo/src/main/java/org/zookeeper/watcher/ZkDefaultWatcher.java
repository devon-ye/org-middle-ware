package org.zookeeper.watcher;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.proto.WatcherEvent;
import org.apache.zookeeper.server.quorum.Election;

import ch.qos.logback.core.joran.action.NewRuleAction;
import ch.qos.logback.core.joran.conditional.ElseAction;

/**
 * Describetion:
 * 
 * @author Devonmusa
 * @version 2017年2月18日 上午3:22:01
 *
 */
public class ZkDefaultWatcher implements Watcher {
	private final LinkedBlockingQueue<WatchedEvent> eventsQueue = new LinkedBlockingQueue<WatchedEvent>();

	public void process(WatchedEvent event) {
		try {
			eventsQueue.put(event);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (KeeperState.SyncConnected == event.getState()) {
			if (EventType.None == event.getType() && null == event.getType()) {
				// countDownLatch.countDown();
			} else if (EventType.NodeCreated == event.getType()) {
				System.err.println("节点（" + event.getPath() + "）被创建");
			} else if (EventType.NodeDeleted == event.getType()) {
				System.err.println("节点（" + event.getPath() + "）被删除");
			} else if (EventType.NodeDataChanged == event.getType()) {
				System.err.println("节点（" + event.getPath() + "）数据改变");
			} else if (EventType.NodeChildrenChanged == event.getType()) {
				System.err.println("子节点（" + event.getPath() + "）改变");
			}
		}
		eventsQueue.poll();

	}

}
