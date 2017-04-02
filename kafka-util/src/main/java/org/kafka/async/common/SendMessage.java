package org.kafka.async.common;
/**
*@see
*@author Devonmusa
*@date   2017年4月1日
*/
public class SendMessage {
	private MessageHeader header;
	private byte[] data;
	
	public SendMessage(MessageHeader header, byte[] data) {
		super();
		this.header = header;
		this.data = data;
	}

	public MessageHeader getHeader() {
		return header;
	}

	public byte[] getData() {
		return data;
	}

	public void setHeader(MessageHeader header) {
		this.header = header;
	}

	public void setData(byte[] data) {
		this.data = data;
	}	
}
