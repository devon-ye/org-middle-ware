package org.kafka.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.devonmusa.util.config.EnvironmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
*@see
*@author  Devonmusa
*@date 2017年4月21日
*/
public class KafkaUtils {
	private static final Logger log = LoggerFactory.getLogger(KafkaUtils.class);
	public static void initLogback() throws FileNotFoundException, IOException, Exception {
		log.info("init logback");
		String appHome = EnvironmentUtils.getAppHome();
		String logBackConfig = appHome + "/config/logback.xml";
		File logBackConfigFile = new File(logBackConfig);
		if (logBackConfigFile.exists()) {
			LoggerContext loggerContext = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);
			loggerContext.reset();
			configurator.doConfigure(logBackConfig);
		}
	}
}