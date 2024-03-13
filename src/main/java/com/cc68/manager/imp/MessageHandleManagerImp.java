package com.cc68.manager.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.manager.MessageHandleManager;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Map;
public class MessageHandleManagerImp implements MessageHandleManager {
    private Client client;

    private Map<String, Handle> handles;
    @Override
    public Message handle(Message message) {
        System.out.println(JSON.toJSONString(message));
        return handles.get(message.getType()).handle(message);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<String, Handle> getHandles() {
        return handles;
    }

    public void setHandles(Map<String, Handle> handles) {
        this.handles = handles;
    }

    public MessageHandleManagerImp() {
    }

    public MessageHandleManagerImp(Client client, Map<String, Handle> handles) {
        this.client = client;
        this.handles = handles;
    }
}
