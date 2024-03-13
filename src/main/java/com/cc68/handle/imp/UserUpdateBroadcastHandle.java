package com.cc68.handle.imp;

import com.cc68.UI.ChatRoomFrame;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("userUpdateBroadcastHandle")
public class UserUpdateBroadcastHandle implements Handle {
    @Autowired
    private Client client;
    @Override
    public Message handle(Message message) {
        HashMap<String, Object> data = message.getData();
        String account = (String) data.get("account");
        String type = (String) data.get("type");
        ApplicationContext context = client.getContext();
        ChatRoomFrame chatRoomFrame = context.getBean("chatRoomFrame", ChatRoomFrame.class);
        ArrayList<String> logoutUsers = chatRoomFrame.getLogoutUsers();
        ArrayList<String> loginUsers = chatRoomFrame.getLoginUsers();
        if (type.equals("register")){
            logoutUsers.add(account);
        }else if (type.equals("connect")){
            logoutUsers.remove(account);
            if (!loginUsers.contains(account)) {
                loginUsers.add(account);
            }
        }else if (type.equals("logout")){
            loginUsers.remove(account);
        }
        chatRoomFrame.flushUser();
        return null;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
