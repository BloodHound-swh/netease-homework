package com.netease.homework.dao;

import com.netease.homework.BaseTest;
import com.netease.homework.entity.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void testQueryProduct() {
        List<Product> productList = productDao.queryProduct();
        assertEquals(1, productList.size());
    }

    @Test
    public void testQueryProductByCondition() {
        Product productCondition = new Product();
        productCondition.setSellerId(3);
        List<Product> productList = productDao.queryProductByCondition(productCondition);
        assertEquals(1, productList.size());
    }

    @Test
    public void testQueryProductById() {
        Integer id = 1;
        Product product = productDao.queryProductById(id);
        System.out.println(product.getProductDesc());
    }

    @Test
    public void testUpdateProduct() {
        int status = 1;
        Integer id = 1;
        Product productCondition = new Product();
        productCondition.setEnableStatus(status);
        productCondition.setProductId(id);
        productDao.updateProduct(productCondition);
    }

    @Test
    public void testInsertProduct() {
        Product product1 = new Product();
        product1.setProductName("测试商品1");
        product1.setProductDesc("测试Desc 1");
        product1.setImgAddr("test1");
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setUpdateTime(new Date());
        product1.setSellerId(3);
        product1.setSellStatus(0);
        product1.setProductId(3);

        int effectedNum = productDao.insertProduct(product1);
        assertEquals(1, effectedNum);
    }
}
