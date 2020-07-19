package com.netease.homework.dao;

import com.netease.homework.BaseTest;
import com.netease.homework.entity.CartItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartItemDaoTest extends BaseTest {
    @Autowired
    private CartItemDao cartItemDao;

    @Test
    public void testQueryCartItemByUserId() {
        Integer userId = 2;
        List<CartItem> cartItemList = cartItemDao.queryCartItemByUserId(userId);
        assertEquals(1, cartItemList.size());
    }
}
