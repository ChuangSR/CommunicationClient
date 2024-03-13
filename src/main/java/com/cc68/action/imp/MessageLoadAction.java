package com.cc68.action.imp;

import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.pojo.Message;
import com.cc68.pojo.MessageDatabase;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service("messageLoadAction")
public class MessageLoadAction implements Action {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Override
    public Message action(Object... data) {
        HashMap<String,Object> temp = null;
        if (data[0] != null){
            temp = new HashMap<>();
            MessageDatabase messageDatabase = (MessageDatabase) data[0];
            temp.put("ID",messageDatabase.getID());
            temp.put("time", String.valueOf(messageDatabase.getTime()));
        }
        return MessageUtil.buildMessage(client.getAccount(),"messageLoad",temp,true);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public MessageLoadAction() {
    }

    public MessageLoadAction(Client client) {
        this.client = client;
    }
}
