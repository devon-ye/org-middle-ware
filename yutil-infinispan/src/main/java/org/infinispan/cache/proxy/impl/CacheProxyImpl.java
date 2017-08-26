package org.infinispan.cache.proxy.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

import org.infinispan.Cache;
import org.infinispan.cache.proxy.ICacheProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@see
*@author  Devonmusa
*@date 2017年4月3日
*/
public class CacheProxyImpl<K,V> implements ICacheProxy{
	private static Logger logger = LoggerFactory.getLogger("CacheProxy");
	
	private Cache<K,V> cache;
	private LinkedBlockingQueue<CompletableFuture<V>>  completableFutureQueue;
	
	public  CacheProxyImpl(Cache<K,V> cache) {
		this.cache = cache;
	}
	
	public CacheProxyImpl(Cache<K,V> cache,LinkedBlockingQueue<CompletableFuture<V>>  completableFutureQueue) {
		this.cache = cache;
		this.completableFutureQueue = completableFutureQueue;
		
	}
	
	public V getCacheValue(K key) {
		return cache.get(key);
	}
	
	public void put(K key,V value) {
		cache.put(key, value);
	}
	
	public void getAsyncCacheValue(K key) {
		CompletableFuture<V>  completableFuture= cache.getAsync(key);
		try {
			completableFutureQueue.put(completableFuture);
		} catch (InterruptedException e) {
			logger.error("getAsyncCacheValue, completableFutureQueue.put is error! InterruptedException:" ,e);
		}
	}
	
	public void putAsync(K key,V value){
		CompletableFuture<V>  completableFuture= cache.putAsync(key, value);
		try {
			completableFutureQueue.put(completableFuture);
		} catch (InterruptedException e) {
			logger.error("putAsync, completableFutureQueue.put is error! InterruptedException:" ,e);

		}
	}
	
}
