package com.cc68.heartbeat;

import java.io.IOException;

/**
 * 用于管理登录客户端的心跳
 * 在超时之后，会将客户端下线
 * */
public interface HeartbeatManger extends Runnable{
    /**
     * 启动心跳管理器
     * */
    void init();
    /**
     * 关闭心跳管理器
     * */
    void close() throws IOException;

    boolean getStatus();
}
