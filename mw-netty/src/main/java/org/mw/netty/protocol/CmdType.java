package org.mw.netty.protocol;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2020/5/11 4:17 PM
 * @description
 */
public enum CmdType {
    SYSTEM(0),
    REQ(1),
    REP(2),
    PUSH(3),
    CHAT(4),
    GROUP(5);


    CmdType(int type) {
    }




}
