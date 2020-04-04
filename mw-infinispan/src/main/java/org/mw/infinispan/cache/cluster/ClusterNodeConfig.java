package org.mw.infinispan.cache.cluster;
/**
*
*@author Devonmusa
*@date   2017年8月27日
*/
public class ClusterNodeConfig {
	
	private String clusterName;
	
	private String rackName;
	
	private String matcheName;
	
	private String siteName;
	
	private String nodeName;
	
	private int clusterSize;
	
	private int initialClusterTimeout;

	public String getClusterName() {
		return clusterName;
	}

	public String getRackName() {
		return rackName;
	}

	public String getMatcheName() {
		return matcheName;
	}

	public String getSiteName() {
		return siteName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public void setMatcheName(String matcheName) {
		this.matcheName = matcheName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getClusterSize() {
		return clusterSize;
	}

	

	public void setClusterSize(int clusterSize) {
		this.clusterSize = clusterSize;
	}

	public int getInitialClusterTimeout() {
		return initialClusterTimeout;
	}

	public void setInitialClusterTimeout(int initialClusterTimeout) {
		this.initialClusterTimeout = initialClusterTimeout;
	}

	

	

	
	
}
