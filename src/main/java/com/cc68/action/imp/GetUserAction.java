package com.cc68.action.imp;

import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("getUserAction")
public class GetUserAction implements Action {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Override
    public Message action(Object... data) {
        return MessageUtil.buildMessage(client.getAccount(),"getUser",null,true);
    }
}
