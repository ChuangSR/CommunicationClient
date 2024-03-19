package com.cc68.action.imp;

import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("unregisterAction")
public class UnregisterAction implements Action {
    @Autowired
    private Client client;
    @Override
    public Message action(Object... data) {
        return MessageUtil.buildMessage(client.getAccount(),"unregister",null,true);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public UnregisterAction() {
    }

    public UnregisterAction(Client client) {
        this.client = client;
    }
}
