package com.netease.homework.dao;

import com.netease.homework.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> queryUser();

    /**
     * 通过条件查询用户
     * @return
     */
    User queryUserByCondition(@Param("userCondition") User userCondition);
}
