package org.infinispan.app;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.infinispan.cache.cluster.jgroup.JgroupConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * @see
 * @author Devonmusa
 * @date 2017年4月3日
 */
public class InfinispanApp {
	private static final Logger logger = LoggerFactory.getLogger(InfinispanApp.class);

	public static void main(String[] args) {
		String userDir = System.getProperty("user.dir");
		System.setProperty("log.home", userDir + "/log");
		try {
			
			initLog4j(userDir);
			
			initLogback(userDir);
			
			initJgroupConfig();
			
 			initCache();

			initHealthyChecks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void initJgroupConfig() {
		JgroupConfig  jgroupConfig=new JgroupConfig("8800");
		jgroupConfig.init();
	}
	
	private static void initCache() {

	}

	private static void initHealthyChecks() {

	}

	private static void initLogback(String appHome) throws Exception {
		logger.info("init logback");
		String logBackConfig = appHome + "/config/logback.xml";
		File logBackConfigFile = new File(logBackConfig);
		if (logBackConfigFile.exists()) {
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);
			loggerContext.reset();
			configurator.doConfigure(logBackConfig);
		}
	}

	private static void initLog4j(String appHome) throws Exception {
		logger.info("init log4j");
		String log4jConfig = appHome + "/config/log4j.properties";
		Properties log4jProperties = new Properties();
		log4jProperties.load(new FileInputStream(log4jConfig));
		PropertyConfigurator.configure(log4jProperties);
	}
}
