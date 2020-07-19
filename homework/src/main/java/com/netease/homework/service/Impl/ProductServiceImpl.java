package com.netease.homework.service.Impl;

import com.netease.homework.dao.ProductDao;
import com.netease.homework.dto.ImageHolder;
import com.netease.homework.dto.ProductExecution;
import com.netease.homework.entity.Product;
import com.netease.homework.enums.ProductStateEnum;
import com.netease.homework.exceptions.ProductOperationException;
import com.netease.homework.service.ProductService;
import com.netease.homework.util.ImageUtil;
import com.netease.homework.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;


    @Override
    public List<Product> getProductList() {
        return productDao.queryProduct();
    }

    @Override
    public List<Product> getProductListByCondition(Product productCondition) {
        return productDao.queryProductByCondition(productCondition);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public int updateProductStatus(Product productCondition) {
        return productDao.updateProduct(productCondition);
    }

    @Override
    public ProductExecution addProduct(Product product, ImageHolder thumbnail) throws ProductOperationException {
        // 空值判断
        if (product != null) {
            // 给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
            // 默认为上架的状态
            product.setEnableStatus(1);
            // 默认为未卖出
            product.setSellStatus(0);
            // 若商品缩略图不为空则添加
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                // 创建商品信息
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败:" + e.toString());
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            // 传参为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail) throws ProductOperationException {
        // 空值判断
        if (product != null) {
            // 给商品设置上默认属性
            product.setUpdateTime(new Date());
            // 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null) {
                // 先获取一遍原有信息，因为原来的信息里有原图片地址
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }

            try {
                // 更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * 添加缩略图
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getProductId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }
}
