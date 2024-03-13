package com.cc68.action;

import com.cc68.pojo.Message;

public interface Action {
    Message action(Object... data);
}
