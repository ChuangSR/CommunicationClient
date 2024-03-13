package com.cc68.manager.imp;

import com.cc68.action.Action;
import com.cc68.client.Client;
import com.cc68.manager.ActionManager;
import com.cc68.pojo.Message;

import java.util.Map;

public class ActionManagerImp implements ActionManager {
    private Client client;
    private Map<String, Action> action;
    @Override
    public Message action(String type, Object... data) {
        return action.get(type).action(data);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<String, Action> getAction() {
        return action;
    }

    public void setAction(Map<String, Action> action) {
        this.action = action;
    }

    public ActionManagerImp() {
    }

    public ActionManagerImp(Client client, Map<String, Action> action) {
        this.client = client;
        this.action = action;
    }
}
