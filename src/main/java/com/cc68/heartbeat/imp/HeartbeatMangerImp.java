package com.cc68.heartbeat.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.client.Client;
import com.cc68.heartbeat.HeartbeatManger;
import com.cc68.manager.SendManager;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("heartbeatManger")
@Scope("prototype")
public class HeartbeatMangerImp implements HeartbeatManger {
    @Autowired
    @Qualifier("client")
    private Client client;
    private SendManager sendManager;
    @Value("${HeartbeatTime}")
    private int heartbeatTime;
    private boolean flag = true;

    private Thread thread;

    public void init(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        String time = MessageUtil.getTime();
        System.out.println(time+"----心跳管理器启动");
        Message message = MessageUtil.buildMessage(client.getAccount(), "Heartbeat", null,false);
        System.out.println(JSON.toJSONString(message));
        while (flag){
            try {
                Thread.sleep(heartbeatTime*1000);
                this.sendManager = client.getContext().getBean("sendManagerHeartbeat",SendManager.class);
                sendManager.send(message);
                sendManager.close();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() throws IOException {
        flag = false;
        if (sendManager != null){
            sendManager.close();
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SendManager getSendManager() {
        return sendManager;
    }

    public void setSendManager(SendManager sendManager) {
        this.sendManager = sendManager;
    }

    public boolean getStatus() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public HeartbeatMangerImp() {
    }

    public HeartbeatMangerImp(Client client, SendManager sendManager, boolean flag) {
        this.client = client;
        this.sendManager = sendManager;
        this.flag = flag;
    }
}
