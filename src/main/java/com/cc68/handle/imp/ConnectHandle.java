package com.cc68.handle.imp;

import com.cc68.UI.LoginFrame;
import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.manager.DaoManager;
import com.cc68.mapper.MessageMapper;
import com.cc68.mapper.UserMapper;
import com.cc68.pojo.Message;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
import java.util.HashMap;

@Service("connectHandle")
public class ConnectHandle implements Handle {
    @Autowired
    @Qualifier("client")
    private Client client;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Override
    public Message handle(Message message) {
        ApplicationContext context = client.getContext();
        LoginFrame loginFrame = context.getBean("loginFrame", LoginFrame.class);
        loginFrame.log(message);
        DaoManager daoManager = client.getDaoManager();
        UserMapper mapper = client.getUserMapper();
        daoManager.Dao(() -> {
            DefaultTransactionDefinition dt = new DefaultTransactionDefinition();
            dt.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus status = platformTransactionManager.getTransaction(dt);
            if (mapper.select(client.getAccount()) == null){
                mapper.insert(client.getAccount(), MessageUtil.getTime());
                MessageMapper messageMapper = client.getMessageMapper();
                messageMapper.createTable(client.getAccount());
            }else {
                mapper.update(client.getAccount(), MessageUtil.getTime());
            }
            platformTransactionManager.commit(status);
            return null;
        },false,"connectHandle");
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        while (true){
//            String temp = (String) daoManager.get("connectHandle");
//            if (temp != null && temp.equals("connectHandle")){
//                break;
//            }
//        }
        return null;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    public ConnectHandle() {
    }

    public ConnectHandle(Client client) {
        this.client = client;
    }
}
