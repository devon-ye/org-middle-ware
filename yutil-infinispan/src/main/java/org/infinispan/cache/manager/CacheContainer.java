package org.infinispan.cache.manager;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月3日
 */
public class CacheContainer {
	private DefaultCacheManager defaultCacheManager;

	private CacheContainer instance = new CacheContainer();

	private CacheContainer() {
	}

	public void init() {

		defaultCacheManager = getDefaultCacheManager();
	}

	public CacheContainer getInstance() {
		return instance;
	}

	public DefaultCacheManager getDefaultCacheManager() {
		return new DefaultCacheManager();
	}

	public void add() {
		Cache<Object,Object> cache = defaultCacheManager.getCache();
		cache.put(new Integer(1),new Long(2) );
		}
}
