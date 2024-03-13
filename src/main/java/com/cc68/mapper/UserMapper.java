package com.cc68.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    String select(String account);
    int update(@Param("account") String account,@Param("time") String time);
    int insert(@Param("account") String account,@Param("time") String time);
}
