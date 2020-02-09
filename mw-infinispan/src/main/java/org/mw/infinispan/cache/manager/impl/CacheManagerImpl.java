package org.mw.infinispan.cache.manager.impl;


import org.mw.infinispan.cache.manager.CacheManagerConfig;
import org.mw.infinispan.cache.manager.ICacheManager;
import org.mw.infinispan.cache.manager.ICacheManagerListener;
import org.infinispan.manager.DefaultCacheManager;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月3日
 */
public class CacheManagerImpl implements ICacheManager {
	private DefaultCacheManager defaultCacheManager;
	private CacheManagerConfig cacheManagerConfig;
	private ICacheManagerListener cacheManagerListener;

	

	public CacheManagerImpl(CacheManagerConfig cacheManagerConfig) {
		this.cacheManagerConfig = cacheManagerConfig;
		this.cacheManagerListener = new CacheManagerListenerImpl();
	}

	public void init() {
		defaultCacheManager =new DefaultCacheManager(cacheManagerConfig.getGlobalConfiguration(), cacheManagerConfig.getDefaultConfiguration());
		defaultCacheManager.addListener(cacheManagerListener);
		defaultCacheManager.start();
	}


	@Override
	public void createCache(String cacheName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startCache(String cacheName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopCache(String cacheName) {
		// TODO Auto-generated method stub
		
	}
}
