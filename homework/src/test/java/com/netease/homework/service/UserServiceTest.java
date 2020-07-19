package com.netease.homework.service;

import com.netease.homework.BaseTest;
import com.netease.homework.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void testGetUserList() {
        List<User> userList = userService.getUserList();
        assertEquals(3, userList.size());
    }

    @Test
    public void testGetUserByCondition() {
        User userCondition = new User();
        userCondition.setUserName("buyer");
        userCondition.setPassWord("reyub");
        User user = userService.getUserByCondition(userCondition);
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
    }
}
