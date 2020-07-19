package com.netease.homework.service;

import com.netease.homework.BaseTest;
import com.netease.homework.dto.ImageHolder;
import com.netease.homework.dto.ProductExecution;
import com.netease.homework.entity.Product;
import com.netease.homework.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testGetProductList() {
        List<Product> productList = productService.getProductList();
        assertEquals(1, productList.size());
    }

    @Test
    public void testGetProductListByCondition() {
        Product productCondition = new Product();
        productCondition.setBuyerId(2);
        List<Product> productList = productService.getProductListByCondition(productCondition);
        assertEquals(1, productList.size());
    }

    @Test
    public void testGetProductById() {
        Integer id = 1;
        Product product = productService.getProductById(id);
        System.out.println(product.getProductDesc());
    }

    @Test
    public void testUpdateProductStatus() {
        Integer status = 1;
        Integer id = 1;
        Product productCondition = new Product();
        productCondition.setProductId(id);
        productCondition.setEnableStatus(status);
        productService.updateProductStatus(productCondition);
    }

    @Test
    public void testAddProduct() throws FileNotFoundException {
        Product product1 = new Product();
        product1.setProductName("测试商品2");
        product1.setProductDesc("测试Desc 2");
        product1.setImgAddr("test2");
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setUpdateTime(new Date());
        product1.setSellerId(3);
        product1.setSellStatus(0);
        product1.setProductId(4);

        File thumbnailFile = new File("/Users/bloodhound/Documents/homework/upload/kafei.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        ProductExecution pe = productService.addProduct(product1, thumbnail);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }
}
