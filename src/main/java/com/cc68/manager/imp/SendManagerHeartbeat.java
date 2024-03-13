package com.cc68.manager.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.manager.SendManager;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
@Controller("sendManagerHeartbeat")
@Scope("prototype")
public class SendManagerHeartbeat implements SendManager {

    private Socket socket;
    private BufferedWriter writer;
    public SendManagerHeartbeat(@Value("${host}") String host,@Value("${heartbeatReceiveManagerPort}") int port) throws IOException {
        this.socket = new Socket(host,port);
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

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

    @Override
    public void init() {

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