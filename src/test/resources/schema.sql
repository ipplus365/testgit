CREATE DATABASE `ipplus360_mall` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use ipplus360_mall;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `organizations`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `carts`;
DROP TABLE IF EXISTS `cart_item`;
DROP TABLE IF EXISTS `price_package`;
DROP TABLE IF EXISTS `user_product_record`;
DROP TABLE IF EXISTS `tokens`;
DROP TABLE IF EXISTS `user_log`;
DROP TABLE IF EXISTS `geoip_version`;

CREATE TABLE `users` (
  id              BIGINT AUTO_INCREMENT COMMENT '主键',
  username        VARCHAR(100) COMMENT '用户名',
  realname        VARCHAR(100) COMMENT '真实姓名',
  email           VARCHAR(100) COMMENT '邮箱',
  mobile          VARCHAR(100) COMMENT '手机号',
  password        VARCHAR(100) COMMENT '密码',
  password_salt   VARCHAR(100) COMMENT '盐',
  organization_id BIGINT COMMENT '所属公司',
  date_created    TIMESTAMP DEFAULT now() COMMENT '创建时间',
  date_updated    TIMESTAMP COMMENT '修改时间',
  last_login      TIMESTAMP COMMENT '上次登录时间',
  last_login_from VARCHAR(100) COMMENT '上次登录IP',
  loginCount      BIGINT COMMENT '登录次数',
  source          TINYINT DEFAULT 0 COMMENT '注册方式',
  productIds      VARCHAR(100) COMMENT '购买产品',
  test_count      TINYINT DEFAULT 3 COMMENT '测试包购买次数',
  test_date_started TIMESTAMP COMMENT '测试包时间限制，一年必须使用完毕',
  status          TINYINT DEFAULT 0 COMMENT '帐号状态',
  description     VARCHAR(255) COMMENT '备注',
  CONSTRAINT pk_users PRIMARY KEY(id)
) CHARSET=utf8 ENGINE=Innodb COMMENT '用户';
CREATE UNIQUE INDEX idx_users_username ON users(username);
CREATE UNIQUE INDEX idx_users_email ON users(email);
CREATE UNIQUE INDEX idx_users_mobile ON users(mobile);
CREATE UNIQUE INDEX idx_users_organization ON users(organization_id);


CREATE TABLE `organizations` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`org_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '公司名称',
	`user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
	`industry` VARCHAR(255) NULL DEFAULT NULL COMMENT '所属行业',
	`legal_person` VARCHAR(255) NULL DEFAULT NULL COMMENT '公司法人',
	`legal_person_id` VARCHAR(255) NULL DEFAULT NULL COMMENT '法人身份证号',
	`budiness_contacts` VARCHAR(255) NULL DEFAULT NULL COMMENT '公司业务联系人',
	`mobile` VARCHAR(100) NULL DEFAULT NULL COMMENT '公司业务联系人手机',
	`email` VARCHAR(100) NULL DEFAULT NULL COMMENT '公司业务联系人邮箱',
	`license_number` VARCHAR(255) NULL DEFAULT NULL COMMENT '营业执照号/统一社会信用代码',
	`license_address` VARCHAR(255) NULL DEFAULT NULL COMMENT '营业执照号所在地',
	`license_start_date` DATE NULL DEFAULT NULL COMMENT '营业执照有效期/营业期限，开始时间',
	`license_end_date` DATE NULL DEFAULT NULL COMMENT '营业执照有效期/营业期限，结束时间',
	`license_dir` VARCHAR(255) NULL DEFAULT NULL COMMENT '营业执照电子版存放路径',
	`bank_account` VARCHAR(100) NULL DEFAULT NULL COMMENT '公司对公账号',
	`bank_user_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '银行开户名',
	`bank` VARCHAR(100) NULL DEFAULT NULL COMMENT '对公账号开户行',
	`available` TINYINT(1) NULL DEFAULT '0' COMMENT '是否认证成功',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `idx_organizations_org_name` (`org_name`)
) COMMENT='组织机构' COLLATE='utf8_general_ci' ENGINE=InnoDB ;

CREATE TABLE `products` (
  id            BIGINT AUTO_INCREMENT COMMENT 'id 自增主键',
  sku_id        VARCHAR(100) COMMENT '商品唯一标识',
  product_name  VARCHAR(100) COMMENT '商品名称',
  icon_url      VARCHAR(100) COMMENT '图标url',
  resource_ids  VARCHAR(255) COMMENT '产品路径API',
  price         DOUBLE COMMENT '商品单价',
  available     BOOL DEFAULT TRUE COMMENT '商品是否可购买',
  description   VARCHAR(255),
  CONSTRAINT pk_products PRIMARY KEY (id)
) CHARSET=utf8 ENGINE=Innodb COMMENT '商品';
CREATE UNIQUE INDEX idx_products_skuId ON products(sku_id);

CREATE TABLE `price_package` (
  id BIGINT AUTO_INCREMENT COMMENT 'id 自增主键',
  product_id BIGINT COMMENT 'product表id',
  type TINYINT COMMENT '套餐类型',
  amount BIGINT COMMENT '套餐包含查询次数',
  price DOUBLE COMMENT '套餐价格',
  discount INT COMMENT '套餐折扣',
  start_date TIMESTAMP COMMENT '有效期开始时间',
  end_date TIMESTAMP COMMENT '有效期截止时间',
  description VARCHAR(255) COMMENT '备注',
  CONSTRAINT pk_price_package PRIMARY KEY (id)
) CHARSET = utf8 ENGINE = Innodb COMMENT '价格套餐';

CREATE TABLE `carts` (
  id           BIGINT       AUTO_INCREMENT COMMENT 'id 自增主键',
  price        DOUBLE       DEFAULT 0 COMMENT '总价钱',
  user_id      BIGINT COMMENT '所属用户',
  status       TINYINT COMMENT '购物车状态',
  date_created TIMESTAMP COMMENT '创建时间',
  date_updated TIMESTAMP COMMENT '更新时间',
  description  VARCHAR(255) COMMENT '备注'
)CHARSET = utf8 ENGINE = Innodb COMMENT '购物车';

CREATE TABLE `cart_item` (
  id BIGINT AUTO_INCREMENT COMMENT 'id 自增主键',
  product_id BIGINT COMMENT '关联products表id',
  price_package_id BIGINT COMMENT '关联price_package表id',
  price DOUBLE COMMENT '购物车项价格',
  CONSTRAINT pk_cart_item PRIMARY KEY (id)
) CHARSET = utf8 ENGINE = Innodb COMMENT '购物车项';

CREATE TABLE `user_product_record` (
  id      BIGINT AUTO_INCREMENT COMMENT 'id 自增主键',
  user_id BIGINT COMMENT '用户ID',
  product_id BIGINT COMMENT '商品ID',
  counts  BIGINT COMMENT '剩余次数',
  CONSTRAINT pk_user_product PRIMARY KEY (id),
  UNIQUE KEY uni_user_product (`user_id`, `product_id`)
) CHARSET = utf8 ENGINE = Innodb COMMENT '用户对应产品API使用次数';

CREATE TABLE `orders` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id 自增主键',
	`order_serial` VARCHAR(100) NULL DEFAULT NULL COMMENT '订单号',
	`price` DOUBLE NULL DEFAULT '0' COMMENT '总价钱',
	`user_id` BIGINT(20) NULL DEFAULT NULL COMMENT '所属用户',
	`status` TINYINT(4) NULL DEFAULT NULL COMMENT '订单状态',
	`date_created` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
	`date_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`description` VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`),
	INDEX `fk_carts_user_id` (`user_id`)
)
COMMENT='订单'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `order_item` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id 自增主键',
	`item_num` INT(10) NOT NULL DEFAULT '1' COMMENT '购买数量',
	`order_id` BIGINT(20) NULL DEFAULT NULL COMMENT '关联orders表id',
	`product_id` BIGINT(20) NULL DEFAULT NULL COMMENT '关联products表id',
	`price_package_id` BIGINT(20) NULL DEFAULT NULL COMMENT '关联price_package表id',
	`price` DOUBLE NULL DEFAULT NULL COMMENT '订单项价格',
	PRIMARY KEY (`id`),
	INDEX `fk_cart_item_product_id` (`product_id`),
	INDEX `fk_cart_item_price_package_id` (`price_package_id`),
	INDEX `fk_cart_item_carts_id` (`order_id`)
)
COMMENT='订单项'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

ALTER TABLE `carts`
	CHANGE COLUMN `date_created` `date_created` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间' AFTER `status`,
	CHANGE COLUMN `date_updated` `date_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' AFTER `date_created`;

ALTER TABLE `cart_item` ADD COLUMN `item_num` INT(11) NULL DEFAULT '1' COMMENT '购买数量' AFTER `id`;
ALTER TABLE `orders` ADD UNIQUE INDEX `order_serial` (`order_serial`);

CREATE TABLE `invoice` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`order_id` BIGINT(20) NOT NULL COMMENT '关联orders表id',
	`org_name` VARCHAR(100) NOT NULL COMMENT '公司名称',
	`tax_id` VARCHAR(100) NOT NULL COMMENT '纳税人识别号',
	`reg_address` VARCHAR(255) NOT NULL COMMENT '注册地址',
	`reg_mobile` VARCHAR(100) NOT NULL COMMENT '注册电话',
	`bank` VARCHAR(100) NOT NULL COMMENT '开户银行',
	`bank_account` VARCHAR(100) NOT NULL COMMENT '银行帐户',
	`user_contacts` VARCHAR(100) NOT NULL COMMENT '收票人姓名',
	`user_mobile` VARCHAR(100) NOT NULL COMMENT '收票人电话',
	`user_address` VARCHAR(255) NOT NULL COMMENT '收票人地址',
	PRIMARY KEY (`id`)
)
COMMENT='发票信息'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `tokens` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id 自增主键',
  `token` VARCHAR(255) COMMENT '用户token',
  user_id BIGINT NOT NULL COMMENT '所属用户id',
  created_date TIMESTAMP NOT NULL COMMENT '生成时间',
  update_date TIMESTAMP COMMENT '更新时间',
  effective_date TIMESTAMP NOT NULL COMMENT '生效时间',
  expire_date TIMESTAMP COMMENT '过期时间',
  product_Ids VARCHAR(255) COMMENT '产品ID',
  available BOOL DEFAULT TRUE COMMENT '状态，是否可用',
  is_test BOOL DEFAULT FALSE COMMENT '测试包token',
  test_count BIGINT(20) COMMENT '测试包次数',
  count BIGINT(20) COMMENT '剩余次数',
  description VARCHAR(255) COMMENT '描述',
  CONSTRAINT pk_tokens PRIMARY KEY (`id`)
)
  CHARSET = utf8
  ENGINE = InnoDB
  COMMENT = '用户token表';

CREATE TABLE `user_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id 自增主键',
  `source` VARCHAR(50) COMMENT '用户查询方式，web，api',
  `is_test` BOOL COMMENT '是否是测试包',
  `log_id` VARCHAR(255) NOT NULL COMMENT '查询记录唯一标识',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `user_ip` VARCHAR(100) COMMENT '客户端IP',
  `token` VARCHAR(255) COMMENT '用户token',
  `data_id` BIGINT NOT NULL COMMENT '对应数据的ID',
  `ip` BIGINT NOT NULL COMMENT '用户查询IP',
  `product_id` BIGINT NOT NULL COMMENT '使用产品',
  `version` VARCHAR(255) NOT NULL COMMENT '数据版本',
  `log_date` TIMESTAMP NOT NULL COMMENT '查询日期',
  CONSTRAINT pk_user_log PRIMARY KEY (`id`)
) CHARSET =utf8 ENGINE InnoDB COMMENT '用户查询记录表,如果一次查询里边包含多款产品,分条记录';

CREATE TABLE `product_desc` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`product_id` BIGINT(20) UNSIGNED NULL DEFAULT NULL COMMENT '对应产品ID',
	`description` LONGTEXT NULL,
	`coverage` LONGTEXT NULL COMMENT '覆盖范围',
	`application_areas` LONGTEXT NULL COMMENT '应用领域',
	`product_features` LONGTEXT NULL COMMENT '产品特点',
	`concurrency` LONGTEXT NULL COMMENT '在线并发',
	`usage_rules` LONGTEXT NULL COMMENT '使用规范',
	`attention` LONGTEXT NULL COMMENT '注意事项',
	`discount_policies` LONGTEXT NULL COMMENT '优惠规则',
	PRIMARY KEY (`id`)
)  CHARSET=utf8 ENGINE InnoDB COMMENT '产品详情介绍';

CREATE TABLE geoip_version (
  `id` BIGINT not null AUTO_INCREMENT,
  `private_version` VARCHAR(255) NOT NULL COMMENT '数据版本-内部版本号',
  `public_version` VARCHAR(255) COMMENT '数据版本-外部版本号(对外),暂时留空',
  `db_name` VARCHAR(255) NOT NULL COMMENT '版本所在数据库名字',
  `table_name` VARCHAR(255) NOT NULL COMMENT '版本表名字',
  `start_time` TIMESTAMP NOT NULL COMMENT '版本上线时间',
  `end_time` TIMESTAMP COMMENT '版本下线时间',
  `desc` VARCHAR(255) COMMENT '备注',
  PRIMARY KEY (`id`)
)  CHARSET=utf8 ENGINE InnoDB COMMENT 'GeoIP数据版本';

ALTER TABLE `orders` ADD COLUMN `is_invoice` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否需要发票' AFTER `description`;

ALTER TABLE `discount`
	ADD COLUMN `accuracy_id` BIGINT(20) NULL DEFAULT NULL COMMENT '关联定位精度accuracy表id' AFTER `id`,
	ADD COLUMN `amount` BIGINT(20) NULL DEFAULT NULL COMMENT '套餐包含查询次数' AFTER `discount`,
	ADD COLUMN `price` DOUBLE NULL DEFAULT NULL COMMENT '套餐价格' AFTER `amount`,
	ADD COLUMN `unit` VARCHAR(20) NULL DEFAULT '元/千次' COMMENT '计价方式' AFTER `price`;
ALTER TABLE `discount`
	CHANGE COLUMN `price_id` `price_pakage_id` BIGINT(20) NULL DEFAULT NULL COMMENT 'pricepackage表id' AFTER `accuracy_id`;

ALTER TABLE `users`
	CHANGE COLUMN `test_count` `test_count` TINYINT(4) NULL DEFAULT '0' COMMENT '测试包购买次数' AFTER `productIds`;
	
ALTER TABLE `cart_item`
	ADD COLUMN `discount` DOUBLE NULL COMMENT '折扣' AFTER `price`,
	ADD COLUMN `original_price` DOUBLE NULL DEFAULT NULL COMMENT '原价' AFTER `discount`;

ALTER TABLE `invoice`
	ADD COLUMN `user_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '关联users表ID' AFTER `id`,
	ADD COLUMN `user_province` VARCHAR(255) NULL NOT NULL COMMENT '收票人省份' AFTER `user_mobile`,
	ADD COLUMN `user_city` VARCHAR(255) NULL DEFAULT NULL COMMENT '收票人城市' AFTER `user_province`,
	ADD COLUMN `user_area` VARCHAR(255) NULL DEFAULT NULL COMMENT '收票人城市' AFTER `user_city`;

