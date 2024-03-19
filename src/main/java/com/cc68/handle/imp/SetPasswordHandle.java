package com.cc68.handle.imp;

import com.cc68.UI.SetPasswordFrame;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("setPasswordHandle")
public class SetPasswordHandle implements Handle {
    @Autowired
    private Client client;
    @Override
    public Message handle(Message message) {
        ApplicationContext context = client.getContext();
        SetPasswordFrame setPasswordFrame = context.getBean("setPasswordFrame", SetPasswordFrame.class);
        setPasswordFrame.log(message.getData());
        return null;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SetPasswordHandle() {
    }

    public SetPasswordHandle(Client client) {
        this.client = client;
    }
}
