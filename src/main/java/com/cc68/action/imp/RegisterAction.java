package com.cc68.action.imp;

import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.manager.ReceiveManager;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service("registerAction")
public class RegisterAction implements Action {
    @Autowired
    @Qualifier("client")
    private Client client;

    @Override
    public Message action(Object... data) {

        String account = (String)data[0];
        String password = (String) data[1];

        HashMap<String,Object> temp = new HashMap<>();
        temp.put("account",account);
        temp.put("password", MessageUtil.getMD5(password));

        client.getSendManager().init();
        ReceiveManager receiveManager = client.getReceiveManager();
        try {
            receiveManager.setSocket(client.getSendManager().getSocket());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread thread = new Thread(receiveManager);
        thread.start();

        return MessageUtil.buildMessage(account,"register",temp,true);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RegisterAction() {
    }

    public RegisterAction(Client client) {
        this.client = client;
    }
}
