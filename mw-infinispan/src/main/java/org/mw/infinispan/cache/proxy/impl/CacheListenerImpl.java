package org.mw.infinispan.cache.proxy.impl;



import org.mw.infinispan.cache.proxy.ICacheListener;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.event.CacheEntriesEvictedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryActivatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryExpiredEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryInvalidatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryLoadedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryPassivatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryVisitedEvent;
import org.infinispan.notifications.cachelistener.event.DataRehashedEvent;
import org.infinispan.notifications.cachelistener.event.PartitionStatusChangedEvent;
import org.infinispan.notifications.cachelistener.event.TopologyChangedEvent;
import org.infinispan.notifications.cachelistener.event.TransactionCompletedEvent;
import org.infinispan.notifications.cachelistener.event.TransactionRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@see
*@author  Devonmusa
*@date 2017年4月3日
*/
@Listener
public class CacheListenerImpl implements ICacheListener {
	private Logger log =  LoggerFactory.getLogger(CacheListenerImpl.class);

	@Override
	public void onCacheEntryActivatedEvent(CacheEntryActivatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCacheEntryCreatedEvent(CacheEntryCreatedEvent event) {
		if (event.isPre()) {
			return;
		}
		log.info("Cache entry %s = %s added in cache %s", event.getKey(), event.getValue(), event.getCache());
		
	}

	@Override
	public void onCacheEntryModifiedEvent(CacheEntryModifiedEvent event) {
		if (event.isPre()) {
			return;
		}
		log.info("Cache entry %s = %s added in cache %s", event.getKey(), event.getValue(), event.getCache());
		
	}

	@Override
	public void onCacheEntryInvalidatedEvent(CacheEntryInvalidatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCacheEntryPassivatedEvent(CacheEntryPassivatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCacheEntryRemovedEvent(CacheEntryRemovedEvent event) {
		if (event.isPre())
			return;
		log.info("Cache entry %s removed in cache %s", event.getKey(), event.getCache());
		
	}

	@Override
	public void onCacheEntryExpiredEvent(CacheEntryExpiredEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCacheEntriesEvictedEvent(CacheEntriesEvictedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCacheEntryVisitedEvent(CacheEntryVisitedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCacheEntryLoadedEvent(CacheEntryLoadedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTopologyChangedEvent(TopologyChangedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPartitionStatusChangedEvent(PartitionStatusChangedEvent envet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDataRehashedEvent(DataRehashedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTransactionCompletedEvent(TransactionCompletedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTransactionRegisteredEvent(TransactionRegisteredEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
