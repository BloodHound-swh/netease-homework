package com.netease.homework.dao;

import com.netease.homework.entity.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemDao {

    /**
     * 根据用户id查询购物车中的商品
     * @param userId
     * @return
     */
    List<CartItem> queryCartItemByUserId(@Param("userId") Integer userId);
}
