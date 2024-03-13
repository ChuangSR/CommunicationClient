package com.cc68.manager;

import com.cc68.pojo.Message;

/**
 * 行为管理器，用户的操作都将被作为一个行为
 * */
public interface ActionManager {
    Message action(String type,Object... data);
}
