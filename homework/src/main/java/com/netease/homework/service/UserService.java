package com.netease.homework.service;

import com.netease.homework.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    /**
     * 根据条件获取用户
     * @return
     */
    User getUserByCondition(User userCondition);
}
