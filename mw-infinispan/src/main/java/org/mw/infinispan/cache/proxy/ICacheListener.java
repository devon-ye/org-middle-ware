package org.mw.infinispan.cache.proxy;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntriesEvicted;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryActivated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryExpired;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryInvalidated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryLoaded;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryPassivated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryVisited;
import org.infinispan.notifications.cachelistener.annotation.DataRehashed;
import org.infinispan.notifications.cachelistener.annotation.PartitionStatusChanged;
import org.infinispan.notifications.cachelistener.annotation.TopologyChanged;
import org.infinispan.notifications.cachelistener.annotation.TransactionCompleted;
import org.infinispan.notifications.cachelistener.annotation.TransactionRegistered;
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

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月19日
 */
@Listener
public interface ICacheListener<K, V> {

	// CACHE_ENTRY_ACTIVATED
	@CacheEntryActivated
	public void onCacheEntryActivatedEvent(CacheEntryActivatedEvent<K, V> event);

	// CACHE_ENTRY_CREATED
	@CacheEntryCreated
	public void onCacheEntryCreatedEvent(CacheEntryCreatedEvent<K, V> event);

	// CACHE_ENTRY_MODIFIED
	@CacheEntryModified
	public void onCacheEntryModifiedEvent(CacheEntryModifiedEvent<K, V> event);

	// CACHE_ENTRY_INVALIDATED
	@CacheEntryInvalidated
	public void onCacheEntryInvalidatedEvent(CacheEntryInvalidatedEvent<K, V> event);

	// CACHE_ENTRY_PASSIVATED
	@CacheEntryPassivated
	public void onCacheEntryPassivatedEvent(CacheEntryPassivatedEvent<K, V> event);

	// CACHE_ENTRY_REMOVED
	@CacheEntryRemoved
	public void onCacheEntryRemovedEvent(CacheEntryRemovedEvent<K, V> event);

	// CACHE_ENTRY_EXPIRED
	@CacheEntryExpired
	public void onCacheEntryExpiredEvent(CacheEntryExpiredEvent<K, V> event);

	// CACHE_ENTRY_EVICTED
	@CacheEntriesEvicted
	public void onCacheEntriesEvictedEvent(CacheEntriesEvictedEvent<K, V> event);

	// CACHE_ENTRY_VISITED
	@CacheEntryVisited
	public void onCacheEntryVisitedEvent(CacheEntryVisitedEvent<K, V> event);

	// CACHE_ENTRY_LOADED
	@CacheEntryLoaded
	public void onCacheEntryLoadedEvent(CacheEntryLoadedEvent<K, V> event);

	// TOPOLOGY_CHANGED
	@TopologyChanged
	public void onTopologyChangedEvent(TopologyChangedEvent<K, V> event);

	// PARTITION_STATUS_CHANGED
	@PartitionStatusChanged
	public void onPartitionStatusChangedEvent(PartitionStatusChangedEvent<K, V> envet);

	// DATA_REHASHED
	@DataRehashed
	public void onDataRehashedEvent(DataRehashedEvent<K, V> event);

	// TRANSACTION_COMPLETED
	@TransactionCompleted
	public void onTransactionCompletedEvent(TransactionCompletedEvent<K, V> event);

	// TRANSACTION_REGISTERED
	@TransactionRegistered
	public void onTransactionRegisteredEvent(TransactionRegisteredEvent<K, V> event);
}
