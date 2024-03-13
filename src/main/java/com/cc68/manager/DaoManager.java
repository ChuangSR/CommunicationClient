package com.cc68.manager;

import com.cc68.action.Dao;

public interface DaoManager extends Runnable{
    void init();
    void Dao(Dao action,boolean flag, String key);

    void close();

    Object get(String key);
}
