package org.infinispan.cache.manager;
/**
*
*@author Devonmusa
*@date   2017年8月27日
*/
public interface ICacheManager {
	
	void createCache(String cacheName);
	
	void startCache(String cacheName);
	
	void stopCache(String cacheName);

}
