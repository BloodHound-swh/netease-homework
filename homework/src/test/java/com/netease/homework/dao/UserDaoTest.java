package com.netease.homework.dao;

import com.netease.homework.BaseTest;
import com.netease.homework.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDaoTest extends BaseTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testQueryUser() {
        List<User> userList = userDao.queryUser();
        assertEquals(3, userList.size());
    }

    @Test
    public void testQueryUserByCondition() {
        User userCondition = new User();
        userCondition.setUserName("buyer");
        userCondition.setPassWord("reyub");
        User user = userDao.queryUserByCondition(userCondition);
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
    }
}
