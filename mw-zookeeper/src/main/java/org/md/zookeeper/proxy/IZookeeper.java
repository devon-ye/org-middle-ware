package org.md.zookeeper.proxy;
/**
*Describetion:
*@author  Devonmusa
*@version
*2017年2月23日 上午12:59:59
*
*/
public interface IZookeeper {
	
	 void init();
	
	 void reTryConnect();
	
	 void createNodeOfPersistent(String path);
	
	 boolean close();
	
}
