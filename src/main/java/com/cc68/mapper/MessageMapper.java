package com.cc68.mapper;

import com.cc68.pojo.MessageDatabase;

import java.util.ArrayList;

public interface MessageMapper {
        void createTable(String account);

        ArrayList<MessageDatabase> selectAllMessage(String account);

        int insertOriginatorMessageDatabase(MessageDatabase messageDatabase);
        int insertReceiverMessageDatabase(MessageDatabase messageDatabase);

//        MessageDatabase selectLastMessage(String account);
}
