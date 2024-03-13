package com.cc68.manager.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.manager.SendManager;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 一个发送器
 * */
@Controller("sendManagerClient")
@Scope("prototype")
public class SendManagerClient implements SendManager {
    @Value("${host}")
    private String host;
    //此处的socket一般是从ReceiveManager中获取的
    @Value("${port}")
    private int port;
    private Socket socket;
    private BufferedWriter writer;

    public SendManagerClient() {
    }
    public void init(){
        try {
            this.socket = new Socket(host,port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 发送一个数据
     * */
    public void send(Message message) throws IOException {
        String data = JSON.toJSONString(message);
        writer.write(data);
        writer.write("\n");
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
        socket.close();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
}
