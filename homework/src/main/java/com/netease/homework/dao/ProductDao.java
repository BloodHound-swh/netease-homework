package com.netease.homework.dao;

import com.netease.homework.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 查询所有上架商品
     * @return
     */
    List<Product> queryProduct();


    /**
     * 根据条件查询商品
     * @param productCondition
     * @return
     */
    List<Product> queryProductByCondition(@Param("productCondition") Product productCondition);

    /**
     * 根据商品ID查询商品
     * @param productId
     * @return
     */
    Product queryProductById(@Param("productId") Integer productId);

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);
}
