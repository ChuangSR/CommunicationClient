package com.cc68.handle.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.UI.LoginFrame;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.heartbeat.HeartbeatManger;
import com.cc68.manager.SendManager;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service("loginHandle")
public class LoginHandle implements Handle {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Override
    public Message handle(Message message) {
        Message replyMessage = null;
        if ("200".equals(message.getData().get("code"))){
            String key = message.getData().get("key").toString();
            HashMap<String,Object> data = new HashMap<>();
            data.put("key",key);


            replyMessage = MessageUtil.replyMessage(client.getAccount(), "connect", data, true);
            ApplicationContext context = client.getContext();
            SendManager sendManager = context.getBean("sendManagerClient", SendManager.class);
            sendManager.init();
            client.setSendManager(sendManager);

            HeartbeatManger heartbeatManger = client.getHeartbeatManger();
            heartbeatManger.init();
        }else {
            client.flush();
        }
        ApplicationContext context = client.getContext();
        LoginFrame loginFrame = context.getBean("loginFrame", LoginFrame.class);
        loginFrame.log(message);
        return replyMessage;
    }
}
