package com.cc68.manager.imp;

import com.cc68.manager.DaoManager;
import com.cc68.action.Dao;
import com.cc68.pojo.DaoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
@Controller("daoManager")
@Scope("prototype")
public class DaoManagerImp implements DaoManager {
    private ArrayList<DaoPojo> list = new ArrayList<>();
    private ArrayList<DaoPojo> temp = new ArrayList<>();
    private ArrayList<DaoPojo> result = new ArrayList<>();

    private boolean connect = false;
    private boolean flag = true;

    public void init(){
        new Thread(this).start();
    }

    @Override
    public void Dao(Dao action,boolean flag, String key) {
        if (key.equals("connectHandle")){
            connect = true;
            list.add(new DaoPojo(key,flag,action));
        }else {
            temp.add(new DaoPojo(key,flag,action));
        }
    }

    @Override
    public void run() {
        while (flag){
            ArrayList<DaoPojo> temp = new ArrayList<>();
            if (connect){
                for (int i = 0;i<list.size();i++){
                    DaoPojo daoPojo = list.get(i);
                    String key = daoPojo.getKey();
                    Object o = daoPojo.getDao().action();
                    if (daoPojo.isFlag()){
                        daoPojo.setO(o);
                        result.add(daoPojo);
                    }
                    temp.add(daoPojo);
                }
                list.clear();
                list.addAll(this.temp);
                this.temp.clear();
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void close(){
        this.flag = false;
    }

    @Override
    public Object get(String key) {
        DaoPojo temp = null;
        for (DaoPojo daoPojo:result){
            if (daoPojo.getKey().equals(key)) {
                temp = daoPojo;
                result.remove(daoPojo);
            }
        }
        return temp;
    }

    public DaoManagerImp() {
    }
}
