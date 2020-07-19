create table `user`(
	`user_id` int(10) NOT NULL AUTO_INCREMENT,
	`user_name` varchar(200) NOT NULL,
	`password` varchar(200) NOT NULL,
	`user_mode` int(2) NOT NULL DEFAULT '1' COMMENT '1:顾客，2:店家，3:超级管理员',
	`create_time` datetime DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
	PRIMARY KEY (`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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

