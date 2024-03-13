package com.cc68.handle.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.UI.ChatRoomFrame;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.manager.DaoManager;
import com.cc68.mapper.MessageMapper;
import com.cc68.pojo.Message;
import com.cc68.pojo.MessageDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
@Service("messageLoadHandle")
public class MessageLoadHandle implements Handle {
    @Autowired
    private Client client;
    @Override
    public Message handle(Message message) {
        HashMap<String, Object> data = message.getData();
        String messages = (String)data.get("messages");
        byte[] bytes = JSON.parseObject(messages,byte[].class);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        ArrayList<MessageDatabase> o = null;
        try {
            ois = new ObjectInputStream(bais);
            o = (ArrayList<MessageDatabase>)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ChatRoomFrame chatRoomFrame = client.getContext().getBean("chatRoomFrame", ChatRoomFrame.class);
        chatRoomFrame.rendering(o);
        return null;
    }
}
