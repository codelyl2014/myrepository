package com.chexiang.dao;

import com.chexiang.entity.User;

public interface UserDao {

    User selectUserByName(String name);
    
    String getUserPassword(String name);

}