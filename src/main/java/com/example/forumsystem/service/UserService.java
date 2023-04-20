package com.example.forumsystem.service;

import com.example.forumsystem.dao.UserMapper;
import com.example.forumsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/4/20 15:04
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }

}
