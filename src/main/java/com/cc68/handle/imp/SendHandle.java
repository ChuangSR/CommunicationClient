package com.cc68.handle.imp;

import com.cc68.UI.ChatRoomFrame;
import com.cc68.client.Client;
import com.cc68.handle.Handle;
import com.cc68.manager.DaoManager;
import com.cc68.mapper.MessageMapper;
import com.cc68.pojo.Message;
import com.cc68.pojo.MessageDatabase;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("sendHandle")
public class SendHandle implements Handle {
    @Autowired
    private Client client;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Override
    public Message handle(Message message) {
        ApplicationContext context = client.getContext();
        DaoManager daoManager = client.getDaoManager();
        MessageMapper messageMapper = client.getMessageMapper();
        MessageDatabase messageDatabase = MessageUtil.toMessageDatabase(message,message.getOriginator());
        daoManager.Dao(() ->{
            DefaultTransactionDefinition dt = new DefaultTransactionDefinition();
            dt.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus status = platformTransactionManager.getTransaction(dt);
            messageMapper.insertReceiverMessageDatabase(messageDatabase);
            platformTransactionManager.commit(status);
            return null;
        },false,"receiver");
        ChatRoomFrame chatRoomFrame = context.getBean("chatRoomFrame", ChatRoomFrame.class);
        chatRoomFrame.flush(messageDatabase,client.getAccount());
        return null;
    }
}
