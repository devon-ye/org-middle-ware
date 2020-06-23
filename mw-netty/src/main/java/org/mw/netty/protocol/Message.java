package org.mw.netty.protocol;


/**
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * |       4      |  8                    | 2      |   2     |   n              |    4         |                                                     |
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * | headerLength | seq                  | cmd     |version | header key value  | bodyLength   | body                                                |
 * |-------------------------------------------------------------------------------------------------------------------------------------------------|
 *
 * @author devon.ye@foxmail.com
 * @datetime 2020/5/11 4:17 PM
 * @description
 */
public class Message implements IMessage {
    private int headerLength;
    private long seq; //指令id
    private short cmd; //指令类型
    private short version;
    private byte[] header;
    private int bodyLength;
    private byte[] body;


    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    public byte[] getHeader() {
        return header;
    }

    public void setHeader(byte[] header) {
        this.header = header;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
