package org.infinispan.cache.manager;

import java.io.Serializable;

import org.infinispan.Cache;

/**
*@see
*@author  Devonmusa
*@date 2017年4月3日
*/
public class CacheWrapper implements Serializable, Comparable<CacheWrapper>{
	
	private int mod;
	
	private Cache cache;
	
	private String cacheName;
	
	private long eventTimeDay;
	

	private CacheWrapper cacheWrapper;
	
	

	public int getMod() {
		return mod;
	}

	public Cache getCache() {
		return cache;
	}

	public String getCacheName() {
		return cacheName;
	}

	public CacheWrapper getCacheWrapper() {
		return cacheWrapper;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public void setCache(Cache cache) {
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

	public void setCacheWrapper(CacheWrapper cacheWrapper) {
		this.cacheWrapper = cacheWrapper;
	}

	@Override
	public int compareTo(CacheWrapper o) {
		return order(o) * -1;
	}
	
	private int order(CacheWrapper o) {
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
