package com.cc68.handle.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("connectTimeoutHandle")
public class ConnectTimeoutHandle implements Handle {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Override
    public Message handle(Message message) {
        System.out.println(JSON.toJSONString(message));
        client.flush();
        return null;
    }
}
