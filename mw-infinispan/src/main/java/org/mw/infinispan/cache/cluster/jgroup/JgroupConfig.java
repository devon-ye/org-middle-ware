package org.mw.infinispan.cache.cluster.jgroup;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Devonmusa
 * @date 2017年8月27日
 */
public class JgroupConfig {
	private static Logger logger = LoggerFactory.getLogger(JgroupConfig.class);
	private String jgroupPort;
	private String jgroupConfigFilePath;
	
	public JgroupConfig(String jgroupPort) {
		this.jgroupPort = jgroupPort;
	}
	
	public JgroupConfig(String jgroupPort,String jgroupConfigFilePath) {
		this.jgroupPort = jgroupPort;
		this.jgroupConfigFilePath = jgroupConfigFilePath;
	}
	
	public String getJgroupPort() {
		return jgroupPort;
	}

	public String getJgroupConfigFilePath() {
		return jgroupConfigFilePath;
	}

	public void setJgroupPort(String jgroupPort) {
		this.jgroupPort = jgroupPort;
	}

	public void setJgroupConfigFilePath(String jgroupConfigFilePath) {
		this.jgroupConfigFilePath = jgroupConfigFilePath;
	}

	public void init() {
		String nodeHost = null;
		try {

			System.setProperty("java.net.preferIPv4Stack", "true");

			nodeHost = System.getProperty("container.nodeHost");
			if (nodeHost == null) {
				InetAddress localHost = InetAddress.getLocalHost();

				nodeHost = localHost.getHostName();
			}

			System.setProperty("jgroupsPort", this.getJgroupPort());
			System.setProperty("jgroups.bind_addr", nodeHost);
			System.setProperty("jgroups.mping.mcast_addr", nodeHost);
			System.setProperty("jgroups.udp.ip_ttl", nodeHost);
			System.setProperty("jgroups.join_timeout", "30000");
			System.setProperty("TCP.diagnostics_addr ", nodeHost);

		} catch (UnknownHostException e) {
			logger.error("initJGourpConfig  UnknownHostException.nodeHost=" + nodeHost, e);
		} catch (Exception e) {
			logger.error("initJGourpConfig  Exception.nodeHost=" + nodeHost, e);
		} catch (Throwable e) {
			logger.error("initJGourpConfig  Throwable.nodeHost=" + nodeHost, e);
		}
	}

}
