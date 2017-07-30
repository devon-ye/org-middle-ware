package org.infinispan.cache.manager;

import java.io.Serializable;

import org.infinispan.Cache;

/**
*@see
*@author  Devonmusa
*@date 2017年4月3日
*/
public class CacheWrapper<K,V> implements Serializable, Comparable<CacheWrapper<K,V>>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int mod;
	
	private Cache<K, V> cache;
	
	private String cacheName;
	
	private long eventTimeDay;
	

	private CacheWrapper<K,V> cacheWrapper;
	
	

	public int getMod() {
		return mod;
	}

	public Cache<K,V> getCache() {
		return cache;
	}

	public String getCacheName() {
		return cacheName;
	}

	public CacheWrapper<K,V> getCacheWrapper() {
		return cacheWrapper;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public void setCache(Cache<K,V> cache) {
		this.cache = cache;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	public long getEventTimeDay() {
		return eventTimeDay;
	}

	public void setEventTimeDay(long eventTimeDay) {
		this.eventTimeDay = eventTimeDay;
	}

	public void setCacheWrapper(CacheWrapper<K,V> cacheWrapper) {
		this.cacheWrapper = cacheWrapper;
	}

	@Override
	public int compareTo(CacheWrapper<K,V> o) {
		return order(o) * -1;
	}
	
	private int order(CacheWrapper<K,V> o) {
		if (this.eventTimeDay > o.getEventTimeDay()) {
			return 1;
		} else if (this.eventTimeDay == o.getEventTimeDay()) {
			if (this.mod > o.getMod()) {
				return 1;
			} else if (this.mod == o.getMod()) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}	
}
