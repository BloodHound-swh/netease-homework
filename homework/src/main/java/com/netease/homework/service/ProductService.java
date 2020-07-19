package com.netease.homework.service;

import com.netease.homework.dto.ImageHolder;
import com.netease.homework.dto.ProductExecution;
import com.netease.homework.entity.Product;
import com.netease.homework.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有上架商品
     * @return
     */
    List<Product> getProductList();

    /**
     * 根据条件查询商品
     * @return
     */
    List<Product> getProductListByCondition(Product productCondition);

    /**
     * 根据商品ID查询商品
     * @param productId
     * @return
     */
    Product getProductById(Integer productId);

    /**
     * 更改商品状态
     * @param productCondition
     * @return
     */
    int updateProductStatus(Product productCondition);

    /**
     * 添加商品信息以及图片处理
     * @param product
     * @param thumbnail
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail) throws ProductOperationException;

    /**
     * 修改商品信息以及图片处理
     * @param product
     * @param thumbnail
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail) throws ProductOperationException;
}
