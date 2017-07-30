package org.infinispan.cache.listener;

import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

/**
*@see
*@author  Devonmusa
*@date 2017年4月19日
*/
public interface IListener {
	
	@CacheEntryCreated
	public void observeAdd(CacheEntryCreatedEvent<Object, Object> event);

	@CacheEntryModified
	public void observeModify(CacheEntryModifiedEvent<Object, Object> event);

	@CacheEntryRemoved
	public void observeRemove(CacheEntryRemovedEvent<Object, Object> event);
}
