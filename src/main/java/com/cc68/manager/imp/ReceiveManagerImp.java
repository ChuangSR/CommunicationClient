package com.cc68.manager.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.client.Client;
import com.cc68.manager.MessageHandleManager;
import com.cc68.manager.ReceiveManager;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 一个接收器
 * */
@Controller("receiveManager")
@Scope("prototype")
public class ReceiveManagerImp implements ReceiveManager {
    @Autowired
    @Qualifier("client")
    private Client client;
    private Socket socket;
    private BufferedReader reader;
    @Autowired
    @Qualifier("messageHandleManager")
    private MessageHandleManager messageHandleManager;
    private boolean flag = true;

    public ReceiveManagerImp(Client client, MessageHandleManager messageHandleManager) {
        this.client = client;
        this.messageHandleManager = messageHandleManager;
    }

    @Override
    public void run() {
        try {
            String data;
            while (flag&&(data = reader.readLine())!=null){
                Message message = JSON.parseObject(data, Message.class);
                Message handle = messageHandleManager.handle(message);
                if (handle != null){
                    client.getSendManager().send(handle);
                }
                if (message.getType().equals("connect")){
//                    Thread.sleep("1");
                };
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public MessageHandleManager getMessageHandleManager() {
        return messageHandleManager;
    }

    public void setMessageHandleManager(MessageHandleManager messageHandleManager) {
        this.messageHandleManager = messageHandleManager;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ReceiveManagerImp() {
    }

    public void close() throws IOException {
        flag = false;
        if (reader != null){
            reader.close();
        }
        if (socket != null){
            socket.close();
        }
    }
}
