ipplus360 mall 2017年2月28日 14:53
数据库更新
ALTER TABLE `cart_item` ADD COLUMN `carts_id` BIGINT(20) NULL DEFAULT NULL COMMENT '关联carts表id' AFTER `id`;
ALTER TABLE `cart_item` ADD CONSTRAINT `fk_cart_item_carts_id` FOREIGN KEY (`carts_id`) REFERENCES `carts` (`id`);

修改表`carts`创建时间字段`date_created`默认值为 null,更新时间字段`date_updated`为更新时修改
ALTER TABLE `carts`
	CHANGE COLUMN `date_created` `date_created` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间' AFTER `status`,
	CHANGE COLUMN `date_updated` `date_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' AFTER `date_created`;