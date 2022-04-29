package com.qd.modules.mnt.websocket;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.websocket
 * @Author: GXW
 * @CreateTime: 2022-04-29  12:46
 * @Description: TODO
 * @Version: 1.0
 */
public enum MsgType {
    /**
     * 链接
     */
    CONNECT,
    /**
     * 关闭
     */
    CLOSE,
    /**
     * 信息
     */
    INFO,
    /**
     * 错误
     */
    ERROR
}
