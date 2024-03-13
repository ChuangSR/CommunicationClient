package com.cc68.action.imp;

import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("logoutAction")
public class LogoutAction implements Action {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Override
    public Message action(Object... data) {
        String account = client.getAccount();
        return MessageUtil.buildMessage(account, "logout", null, false);
    }
}
