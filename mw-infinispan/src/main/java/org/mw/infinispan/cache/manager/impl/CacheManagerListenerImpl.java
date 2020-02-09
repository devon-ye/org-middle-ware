package org.mw.infinispan.cache.manager.impl;

import org.mw.infinispan.cache.manager.ICacheManagerListener;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStoppedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.MergeEvent;
import org.infinispan.notifications.cachemanagerlistener.event.ViewChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
*@author Devonmusa
*@date   2017年8月27日
*/
public class CacheManagerListenerImpl implements ICacheManagerListener {
	private static Logger logger = LoggerFactory.getLogger(CacheManagerListenerImpl.class);

	@Override
	public void onCacheStartedEvent(CacheStartedEvent event) {
		logger.info("onCacheStartedEvent, CacheStartedEvent=" +event);

	}

	@Override
	public void onCacheStoppedEvent(CacheStoppedEvent event) {
		logger.info("onCacheStoppedEvent, CacheStoppedEvent=" +event);

	}

	@Override
	public void onViewChangedEvent(ViewChangedEvent event) {
		logger.info("onViewChangedEvent, ViewChangedEvent=" +event);

	}

	@Override
	public void onMergeEvent(MergeEvent event) {
		logger.info("onMergeEvent, MergeEvent=" +event);

	}

}
