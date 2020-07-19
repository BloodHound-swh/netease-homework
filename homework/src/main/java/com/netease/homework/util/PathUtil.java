package com.netease.homework.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    /**
     * 获取图片根目录
     * @return
     */
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/homework";
        } else {
            basePath = "/Users/bloodhound/Documents/homework";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    /**
     * 根据商品ID获取商品图片目录
     * @param productId
     * @return
     */
    public static String getShopImagePath(Integer productId) {
        String imagePath = "/homework-images/items/" + productId + "/";
        return imagePath.replace("/", seperator);
    }
}
