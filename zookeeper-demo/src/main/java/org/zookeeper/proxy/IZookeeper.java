package org.zookeeper.proxy;
/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月23日 上午12:59:59
*
*/
public interface IZookeeper {
	
	public void init();
	
	public void connect();
	
	public void reTryConnect();
	
	public void createNodeOfPersistent();
	
	public void close();
	
}
