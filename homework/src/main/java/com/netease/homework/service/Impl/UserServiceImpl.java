package com.netease.homework.service.Impl;

import com.netease.homework.dao.UserDao;
import com.netease.homework.entity.User;
import com.netease.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getUserList() {
        return userDao.queryUser();
    }

    @Override
    public User getUserByCondition(User userCondition) {
        return userDao.queryUserByCondition(userCondition);
    }

}
