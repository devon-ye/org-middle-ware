package org.mw.infinispan.cache.manager;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStarted;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStopped;
import org.infinispan.notifications.cachemanagerlistener.annotation.Merged;
import org.infinispan.notifications.cachemanagerlistener.annotation.ViewChanged;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStoppedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.MergeEvent;
import org.infinispan.notifications.cachemanagerlistener.event.ViewChangedEvent;

/**
 *
 * @author Devonmusa
 * @date 2017年8月27日
 */
@Listener
public interface ICacheManagerListener {

	@CacheStarted
	void onCacheStartedEvent(CacheStartedEvent event);

	@CacheStopped
	void onCacheStoppedEvent(CacheStoppedEvent event);

	@ViewChanged
	void onViewChangedEvent(ViewChangedEvent event);

	@Merged
	void onMergeEvent(MergeEvent event);
}
