# 设计文档

卖家

- 用户名：seller
- 密码：relles

买家

- 用户名：buyer
- 密码：reyub



### 环境

- Java 8
- MySQL Server 8.0
- Spring + SpringMVC + MyBatis



### 数据库设计

**用户表**

```mysql
create table `user`(
	`user_id` int(10) NOT NULL AUTO_INCREMENT,
	`user_name` varchar(200) NOT NULL,
	`password` varchar(200) NOT NULL,
	`user_mode` int(2) NOT NULL DEFAULT '1' COMMENT '1:顾客，2:店家，3:超级管理员',
	`create_time` datetime DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
	PRIMARY KEY (`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```



**商品表**

```mysql
CREATE TABLE `product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `product_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0:下架，1:上架',
  `sell_status` int(2) NOT NULL DEFAULT '0' COMMENT '0:未售出，1:已售出',
  `seller_id` int(20) NOT NULL DEFAULT '0',
  `buyer_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```



**购物车**

```mysql
create table `cart` (
    `cart_id` int(100) NOT NULL AUTO_INCREMENT,
    `user_id` int(20) NOT NULL DEFAULT '0',
    `create_time` datetime DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table `cart_item` (
    `cart_item_id` int(100) NOT NULL AUTO_INCREMENT,
    `user_id` int(20) NOT NULL DEFAULT '0',
    `product_id` int(100) NOT NULL,
    `quantity` int(20) NOT NULL DEFAULT '0',
    `create_time` datetime DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    PRIMARY KEY (`cart_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```



**订单**

```mysql
create table `order_master` (
    `order_master_id` int(100) NOT NULL AUTO_INCREMENT,
    `user_id` int(20) NOT NULL DEFAULT '0',
    `user_name` varchar(200) NOT NULL,
    `order_master_amount` varchar(100) DEFAULT NULL,
    `create_time` datetime DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    PRIMARY KEY (`order_master_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table `order_detail` (
    `order_detail_id` int(100) NOT NULL AUTO_INCREMENT,
    `user_id` int(20) NOT NULL DEFAULT '0',
    `user_name` varchar(200) NOT NULL,
    `order_amount` varchar(100) DEFAULT NULL,
    `order_name` varchar(200) NOT NULL,
    `create_time` datetime DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    PRIMARY KEY (`order_detail_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```



### 完成的功能

**展示**

系统的基本界面为卖家所有内容的展示

商品细节展示为标题、图片和价格

买家登陆后，已购买的内容上有特殊标示表明已购买

买家登陆后，可以只查看未购买的内容



**登录**

用户登陆时，需要输入用户名和密码



**发布**

卖家在展示界面点击”发布“按钮，进入发布界面。

发布界面可以输入内容的标题、摘要、图片和价格，点击发布按钮即发布回到查看页面



**内容的编辑**

卖家在查看界面可以点击编辑按钮进入编辑页面

可以修改内容的全部细节：标题、摘要、图片和价格，单机提交后回到查看界面