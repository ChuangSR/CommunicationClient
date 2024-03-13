package com.cc68.client.imp;

import com.alibaba.fastjson2.JSON;
import com.cc68.UI.ChatRoomFrame;
import com.cc68.client.Client;
import com.cc68.heartbeat.HeartbeatManger;
import com.cc68.manager.ActionManager;
import com.cc68.manager.DaoManager;
import com.cc68.manager.ReceiveManager;
import com.cc68.manager.SendManager;
import com.cc68.mapper.MessageMapper;
import com.cc68.mapper.UserMapper;
import com.cc68.pojo.Message;
import com.cc68.pojo.MessageDatabase;
import com.cc68.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
@Component("client")
public class ClientImp implements Client {
    /**
     * 用户账户
     * */
    private String account;
    /**
     * 心跳管理器
     * */
    @Autowired
    @Qualifier("heartbeatManger")
    private HeartbeatManger heartbeatManger;
    /**
     * 行为处理器
     * */
    @Autowired
    @Qualifier("actionManager")
    private ActionManager actionManager;
    /**
     * 消息发送器
     * */
    @Autowired
    @Qualifier("sendManagerClient")
    private SendManager sendManager;
    /**
     * 消息接收器
     * */
    @Autowired
    @Qualifier("receiveManager")
    private ReceiveManager receiveManager;
    /**
     * 表示客户端状态
     * */
    private boolean status = false;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ApplicationContext context;
    @Autowired
    @Qualifier("daoManager")
    private DaoManager daoManager;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public void action(String type,Object... data) throws IOException {
        Message action = actionManager.action(type, data);
        System.out.println(JSON.toJSONString(action));
        if (action != null){
            sendManager.send(action);
            if ("logout".equals(action.getType())){
                this.close();
            }else if ("send".equals(action.getType())){
                MessageDatabase messageDatabase = MessageUtil.toMessageDatabase(action,action.getReceiver());
                daoManager.Dao(() ->{
                    DefaultTransactionDefinition dt = new DefaultTransactionDefinition();
                    dt.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                    TransactionStatus status = platformTransactionManager.getTransaction(dt);
                    messageMapper.insertOriginatorMessageDatabase(messageDatabase);
                    platformTransactionManager.commit(status);
                    return null;
                },false,"send");
                ChatRoomFrame chatRoomFrame = context.getBean("chatRoomFrame", ChatRoomFrame.class);
                chatRoomFrame.flush(messageDatabase,account);
            }
        }
    }

    @Override
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public HeartbeatManger getHeartbeatManger() {
        return heartbeatManger;
    }

    public void setHeartbeatManger(HeartbeatManger heartbeatManger) {
        this.heartbeatManger = heartbeatManger;
    }

    @Override
    public SendManager getSendManager() {
        return sendManager;
    }

    public void setSendManager(SendManager sendManager) {
        this.sendManager = sendManager;
    }

    public ReceiveManager getReceiveManager() {
        return receiveManager;
    }

    public void setReceiveManager(ReceiveManager receiveManager) {
        this.receiveManager = receiveManager;
    }

    public boolean isStatus() {
        return status;
    }

    public PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }

    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public MessageMapper getMessageMapper() {
        return messageMapper;
    }

    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    public void flush(){
        this.sendManager = context.getBean("sendManagerClient",SendManager.class);
        this.receiveManager = context.getBean("receiveManager",ReceiveManager.class);
        if (heartbeatManger.getStatus()){
            this.heartbeatManger = context.getBean("heartbeatManger", HeartbeatManger.class);
        }
        this.daoManager = context.getBean("daoManager", DaoManager.class);
        daoManager.init();
    }

    @Override
    public void close() throws IOException {
        heartbeatManger.close();
        receiveManager.close();
        sendManager.close();
        daoManager.close();
    }

    public ClientImp() {

    }

    public ClientImp(String account, HeartbeatManger heartbeatManger, ActionManager actionManager, SendManager sendManager, ReceiveManager receiveManager, boolean status) {
        this.account = account;
        this.heartbeatManger = heartbeatManger;
        this.actionManager = actionManager;
        this.sendManager = sendManager;
        this.receiveManager = receiveManager;
        this.status = status;
    }
}
