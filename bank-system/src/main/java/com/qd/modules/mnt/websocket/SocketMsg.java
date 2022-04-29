package com.qd.modules.mnt.websocket;

import lombok.Data;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.websocket
 * @Author: GXW
 * @CreateTime: 2022-04-29  12:51
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SocketMsg {
    private String msg;
    private MsgType msgType;

    public SocketMsg(String msg, MsgType msgType) {
        this.msg = msg;
        this.msgType = msgType;
    }
}
