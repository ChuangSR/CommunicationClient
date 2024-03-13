package com.cc68.pojo;

import com.cc68.action.Dao;

public class DaoPojo {
    private String key;

    private Object o;

    private boolean flag;

    private Dao dao;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public DaoPojo(String key, boolean flag, Dao dao) {
        this.key = key;
        this.flag = flag;
        this.dao = dao;
    }
}
