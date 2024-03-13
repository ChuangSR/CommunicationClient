package com.cc68.handle.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.UI.ChatRoomFrame;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("getUserHandle")
public class GetUserHandle implements Handle {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Override
    public Message handle(Message message) {
        HashMap<String, Object> data = message.getData();
        int loginUsersSize = Integer.parseInt(String.valueOf(data.get("LoginUsers")));
        int allUsersSize = Integer.parseInt(String.valueOf(data.get("AllUsers")));
        ArrayList<HashMap<String,String>> users = (ArrayList<HashMap<String,String>>)data.get("Users");

        ArrayList<String> loginUsers = new ArrayList<>(loginUsersSize);
        ArrayList<String> logoutUsers = new ArrayList<>(allUsersSize);
        for (HashMap<String,String> temp:users){
            if (Boolean.parseBoolean(temp.get("status"))){
                loginUsers.add(temp.get("account"));
            }else {
                logoutUsers.add(temp.get("account"));
            }
        }
        ApplicationContext context = client.getContext();
        ChatRoomFrame chatRoomFrame = context.getBean("chatRoomFrame", ChatRoomFrame.class);
        chatRoomFrame.setUsers(loginUsers,logoutUsers);
        return null;
    }
}
