package org.mw.kafka.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
*
*@author  Devonmusa
*@date 2017年4月21日
*/
public class KafkaUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtils.class);
	
	public static void initLogback() throws FileNotFoundException, IOException, Exception {
		LOG.info("start init logback!");
		String appHome = System.getProperty("user.dir");
		System.setProperty("log.home", appHome +"/log");
		System.setProperty("log.dir", appHome +"/log");
		String logBackConfig = appHome + "/config/log/logback.xml";
		File logBackConfigFile = new File(logBackConfig);
		if (logBackConfigFile.exists()) {
			LoggerContext loggerContext = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);
			loggerContext.reset();
			configurator.doConfigure(logBackConfig);
		}
		LOG.info("end init logback");
	}
	
	public static void initLog4j() {
		
	}
}
