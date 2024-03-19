package com.cc68.action.imp;

import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("setPasswordAction")
public class SetPasswordAction implements Action {
    @Autowired
    private Client client;
    @Override
    public Message action(Object... data) {
        String password = (String) data[0];
        HashMap<String,Object> temp = new HashMap<>();
        temp.put("password",MessageUtil.getMD5(password));
        return MessageUtil.buildMessage(client.getAccount(),"setPassword",temp,true);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SetPasswordAction() {
    }

    public SetPasswordAction(Client client) {
        this.client = client;
    }
}
