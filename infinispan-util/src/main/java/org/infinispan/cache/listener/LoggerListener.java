package org.infinispan.cache.listener;



import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@see
*@author  Devonmusa
*@date 2017年4月3日
*/
@Listener
public class LoggerListener {
	private Logger log =  LoggerFactory.getLogger(LoggerListener.class);
	@CacheEntryCreated
	 public void observeAdd(CacheEntryCreatedEvent<Object, Object> event) {
	      if (event.isPre()){
	         return;
	      }
	      log.info("Cache entry %s = %s added in cache %s", event.getKey(), event.getValue(), event.getCache());
	 }
	
	@CacheEntryModified
	public void observeModify(CacheEntryModifiedEvent<Object,Object> event){
		 if (event.isPre()){
	         return;
		 }
	      log.info("Cache entry %s = %s added in cache %s", event.getKey(), event.getValue(), event.getCache());
	}
	
	
	@CacheEntryRemoved
	public void observeRemove(CacheEntryRemovedEvent<Object, Object> event) {
	      if (event.isPre())
	         return;
	      log.info("Cache entry %s removed in cache %s", event.getKey(), event.getCache());
	   }
	
}
