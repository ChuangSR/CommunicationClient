package com.cc68.client;

import com.cc68.heartbeat.HeartbeatManger;
import com.cc68.manager.DaoManager;
import com.cc68.manager.ReceiveManager;
import com.cc68.manager.SendManager;
import com.cc68.mapper.MessageMapper;
import com.cc68.mapper.UserMapper;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public interface Client {
    String getAccount();
    SendManager getSendManager();
    boolean isStatus();
    void setStatus(boolean status);
    void close() throws IOException;
    void action(String type,Object... data) throws IOException;
    ReceiveManager getReceiveManager();
    ApplicationContext getContext();
    void setContext(ApplicationContext context);
    void setSendManager(SendManager sendManager);
    HeartbeatManger getHeartbeatManger();
    void setAccount(String account);
    void flush();
    UserMapper getUserMapper();
    MessageMapper getMessageMapper();
    DaoManager getDaoManager();

    void setDaoManager(DaoManager daoManager);
}
