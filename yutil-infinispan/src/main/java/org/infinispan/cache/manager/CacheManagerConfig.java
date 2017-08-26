package org.infinispan.cache.manager;

import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.global.GlobalConfiguration;

/**
*
*@author Devonmusa
*@date   2017年8月27日
*/
public class CacheManagerConfig {
	private String cacheManagerName;
	private  int numOwners;
	
	private GlobalConfiguration globalConfiguration;
	private Configuration defaultConfiguration;
	public String getCacheManagerName() {
		return cacheManagerName;
	}
	public int getNumOwners() {
		return numOwners;
	}
	public GlobalConfiguration getGlobalConfiguration() {
		return globalConfiguration;
	}
	public Configuration getDefaultConfiguration() {
		return defaultConfiguration;
	}
	public void setCacheManagerName(String cacheManagerName) {
		this.cacheManagerName = cacheManagerName;
	}
	public void setNumOwners(int numOwners) {
		this.numOwners = numOwners;
	}
	public void setGlobalConfiguration(GlobalConfiguration globalConfiguration) {
		this.globalConfiguration = globalConfiguration;
	}
	public void setDefaultConfiguration(Configuration defaultConfiguration) {
		this.defaultConfiguration = defaultConfiguration;
	}
	
	

}
