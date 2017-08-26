package org.infinispan.cache.manager.impl;

import org.infinispan.cache.manager.ICacheManagerListener;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStoppedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.MergeEvent;
import org.infinispan.notifications.cachemanagerlistener.event.ViewChangedEvent;

/**
*
*@author Devonmusa
*@date   2017年8月27日
*/
public class CacheManagerListenerImpl implements ICacheManagerListener {

	@Override
	public void onCacheStartedEvent(CacheStartedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCacheStoppedEvent(CacheStoppedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onViewChangedEvent(ViewChangedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMergeEvent(MergeEvent event) {
		// TODO Auto-generated method stub

	}

}
