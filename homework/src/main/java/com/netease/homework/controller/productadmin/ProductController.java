package com.netease.homework.controller.productadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.homework.dto.ImageHolder;
import com.netease.homework.dto.ProductExecution;
import com.netease.homework.entity.Product;
import com.netease.homework.entity.User;
import com.netease.homework.enums.ProductStateEnum;
import com.netease.homework.exceptions.ProductOperationException;
import com.netease.homework.service.ProductService;
import com.netease.homework.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productadmin")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/listproduct", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listProduct() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Product> productList;
        try {
            productList = productService.getProductList();
            modelMap.put("success", true);
            modelMap.put("productList", productList);
            modelMap.put("totalNum", productList.size());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        return modelMap;
    }

    @RequestMapping(value = "/listsellerproduct", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listSellerProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            User seller = (User) request.getSession().getAttribute("currentUser");
            Product productCondition = new Product();
            productCondition.setSellerId(seller.getUserId());
            List<Product> productList = productService.getProductListByCondition(productCondition);
            modelMap.put("success", true);
            modelMap.put("productList", productList);
            modelMap.put("totalNum", productList.size());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        return modelMap;
    }

    @RequestMapping(value = "/listnopurchased", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listNoPurchased() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Product> productList;
        Product productCondition = new Product();
        productCondition.setSellStatus(0);
        productCondition.setEnableStatus(1);
        try {
            productList = productService.getProductListByCondition(productCondition);
            modelMap.put("success", true);
            modelMap.put("productList", productList);
            modelMap.put("totalNum", productList.size());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        return modelMap;
    }

    @RequestMapping(value = "/productdetail", method = RequestMethod.GET)
    private String productdetail() {
        return "product/productdetail";
    }

    @RequestMapping(value = "/getproduct", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取前台传递过来的productId
        Integer productId = HttpServletRequestUtil.getInt(request, "productId");
        if (productId != -1) {
            Product product = productService.getProductById(productId);
            modelMap.put("product", product);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }

        return modelMap;
    }

    @RequestMapping(value = "/changestatus", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeStatus(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            // 尝试获取前端传过来的表单string流并将其转换成Product实体类
            Product product = mapper.readValue(productStr, Product.class);
            productService.updateProductStatus(product);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        return modelMap;
    }

    @RequestMapping(value = "/productoperation", method = RequestMethod.GET)
    private String productOperation() {
        return "/product/productoperation";
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 接收前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder thumbnail = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            // 若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
            if (multipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request, thumbnail);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能为空");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        try {
            // 尝试获取前端传过来的表单string流并将其转换成Product实体类
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        // 若Product信息，缩略图以及详情图列表为非空，则开始进行商品添加操作
        if (product != null && thumbnail != null) {
            try {
                // 从session中获取当前店铺的Id并赋值给product，减少对前端数据的依赖
                User currentUser = (User) request.getSession().getAttribute("currentUser");
                product.setSellerId(currentUser.getUserId());
                // 执行添加操作
                ProductExecution pe = productService.addProduct(product, thumbnail);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 接收前端参数的变量的初始化，包括商品，缩略图
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        ImageHolder thumbnail = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
        try {
            if (multipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request, thumbnail);
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            // 尝试获取前端传过来的表单string流并将其转换成Product实体类
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        // 非空判断
        if (product != null) {
            try {
                // 从session中获取当前店铺的Id并赋值给product，减少对前端数据的依赖
                User currentUser = (User) request.getSession().getAttribute("currentUser");
                product.setSellerId(currentUser.getUserId());
                // 开始进行商品信息变更操作
                ProductExecution pe = productService.modifyProduct(product, thumbnail);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }

        return modelMap;
    }

    /**
     * 图片预处理
     * @param request
     * @param thumbnail
     * @return
     * @throws IOException
     */
    private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 取出缩略图并构建ImageHolder对象
        CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
        if (thumbnailFile != null) {
            thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
        }
        return thumbnail;
    }
}
