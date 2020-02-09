package org.infinispan.cache.cluster.transport;

import java.util.concurrent.TimeUnit;

import org.infinispan.cache.cluster.ClusterNodeConfig;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.configuration.global.TransportConfigurationBuilder;


/**
*
*@author Devonmusa
*@date   2017年8月27日
*/
public class TransportConfig {
	private GlobalConfigurationBuilder globalConfigurationBuilder;
	private ClusterNodeConfig clusterNodeConfig;
	
	public TransportConfig(GlobalConfigurationBuilder globalConfigurationBuilder, ClusterNodeConfig clusterNodeConfig) {
		this.globalConfigurationBuilder = globalConfigurationBuilder;
		this.clusterNodeConfig = clusterNodeConfig;
	}
	
	public TransportConfigurationBuilder getTransportConfigurationBuilder() {
		TransportConfigurationBuilder transportConfigurationBuilder = globalConfigurationBuilder.transport().defaultTransport();

		// CLUSTER_NAME,
		transportConfigurationBuilder.clusterName(clusterNodeConfig.getClusterName());
		// MACHINE_ID,
		transportConfigurationBuilder.machineId(clusterNodeConfig.getMatcheName());
		// RACK_ID,
		transportConfigurationBuilder.rackId(clusterNodeConfig.getRackName());
		// SITE_ID,
		transportConfigurationBuilder.siteId(clusterNodeConfig.getSiteName());
		// NODE_NAME,
		transportConfigurationBuilder.nodeName(clusterNodeConfig.getNodeName());
		// DISTRIBUTED_SYNC_TIMEOUT,

		// INITIAL_CLUSTER_SIZE,
		transportConfigurationBuilder.initialClusterSize(clusterNodeConfig.getClusterSize());

		// INITIAL_CLUSTER_TIMEOUT,
		transportConfigurationBuilder.initialClusterTimeout(clusterNodeConfig.getInitialClusterTimeout(), TimeUnit.SECONDS);
		// TRANSPORT,
		
		return transportConfigurationBuilder;
	}

}
