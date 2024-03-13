package com.cc68.handle.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.UI.RegisterFrame;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service("registerHandle")
public class RegisterHandle implements Handle {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Override
    public Message handle(Message message) {
        HashMap<String, Object> data = message.getData();
        ApplicationContext context = client.getContext();
        RegisterFrame registerFrame = context.getBean("registerFrame", RegisterFrame.class);
        registerFrame.log(data);
        return null;
    }

    public RegisterHandle() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
