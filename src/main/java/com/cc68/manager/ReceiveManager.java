package com.cc68.manager;

import com.cc68.pojo.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 用于接收数据的一个类
 * */
public interface ReceiveManager extends Runnable{
    void setSocket(Socket socket) throws IOException;
    void close() throws IOException;
}
