package org.mw.lucene.index.model;

import java.util.HashSet;

/**
*
*@author Devonmusa
*@date   2017年8月17日
*/
public class IndexConfig {
	private static HashSet<ConfigBean> configBeans;
	
	
	private static class DefaultIndexConfig {
		private static final HashSet<ConfigBean> configBeanDefault = new HashSet<>();
		
		static {
			ConfigBean configBean = new ConfigBean();
			configBeanDefault.add(configBean);
		}
	}


	public static HashSet<ConfigBean> getConfigBeans() {
		if(configBeans == null) {
			return DefaultIndexConfig.configBeanDefault;
		}
		return configBeans;
	}


	public static void setConfigBeans(HashSet<ConfigBean> configBeans) {
		IndexConfig.configBeans = configBeans;
	}
	
	
	

}
