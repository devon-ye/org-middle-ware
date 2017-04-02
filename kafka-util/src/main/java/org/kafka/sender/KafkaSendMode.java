package org.kafka.sender;
/**
*@see
*@author  Devonmusa
*@date 2017年4月2日
*/
public enum KafkaSendMode {
   Async(1),Sync(2);
   int value;
   KafkaSendMode(int value){
	   this.value =value;
   }
}
