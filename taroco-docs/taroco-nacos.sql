/*
Navicat MySQL Data Transfer

Source Server         : 本机root
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : taroco

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-02-13 16:02:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('10', '顶级部门', '0', '2018-08-20 11:46:57', null, '0', '0');
INSERT INTO `sys_dept` VALUES ('11', '一级部门', '0', '2018-08-20 11:47:10', null, '0', '10');
INSERT INTO `sys_dept` VALUES ('12', '二级部门', '1', '2018-08-20 11:47:19', null, '0', '11');

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`),
  KEY `idx1` (`ancestor`) USING BTREE,
  KEY `idx2` (`descendant`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES ('10', '10');
INSERT INTO `sys_dept_relation` VALUES ('10', '11');
INSERT INTO `sys_dept_relation` VALUES ('10', '12');
INSERT INTO `sys_dept_relation` VALUES ('11', '11');
INSERT INTO `sys_dept_relation` VALUES ('11', '12');
INSERT INTO `sys_dept_relation` VALUES ('12', '12');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(50) NOT NULL COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `time` mediumtext COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `path` varchar(255) DEFAULT NULL COMMENT '前端路径',
  `url` varchar(255) DEFAULT NULL COMMENT '外部链接',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '0--正常 1--删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '/admin', null, '-1', 'cog', '1', '2017-11-07 20:56:00', '2018-09-08 01:46:08', '0');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', 'user', '', '1', 'file-o', '2', '2017-11-02 22:24:37', '2018-09-08 02:01:44', '0');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', 'menu', '', '1', 'file-o', '3', '2017-11-08 09:57:27', '2018-09-08 02:01:45', '0');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', 'role', null, '1', 'file-o', '4', '2017-11-08 10:13:37', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('5', '日志管理', 'log', null, '1', 'file-o', '5', '2017-11-20 14:06:22', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('6', '字典管理', 'dict', null, '1', 'file-o', '6', '2017-11-29 11:30:52', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('7', '部门管理', 'dept', null, '1', 'file-o', '7', '2018-01-20 13:17:19', '2018-09-08 02:01:49', '0');
INSERT INTO `sys_menu` VALUES ('8', '服务管理', '/taroco-admin', null, '-1', 'line-chart', '8', '2018-01-22 12:30:41', '2019-02-13 10:54:32', '0');
INSERT INTO `sys_menu` VALUES ('14', '接口文档', 'taroco-api', '', '8', 'buysellads', '2', '2018-01-23 10:56:43', '2018-09-10 06:58:57', '0');
INSERT INTO `sys_menu` VALUES ('100', '客户端管理', 'client', '', '1', 'file-o', '9', '2018-01-20 13:17:19', '2018-09-08 02:02:02', '0');
INSERT INTO `sys_menu` VALUES ('110', '路由管理', 'route', null, '1', 'file-o', '8', '2018-05-15 21:44:51', '2018-09-08 02:02:07', '0');

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(40) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('taroco', null, '$2a$10$drYsSntNKIr.cCiAQip0uOp5VtZl2FWZ4WvNYgLcMb19ri66mVzRS', 'server', 'password,refresh_token,authorization_code', null, null, null, null, null, 'false');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_idx1_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'ROLE_ADMIN', '超级管理员', '2017-10-29 15:45:51', '2018-08-09 08:51:09', '0');
INSERT INTO `sys_role` VALUES ('14', 'demo', 'demo', 'demo用户', '2018-04-20 07:14:32', '2018-04-21 23:35:22', '1');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` int(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('14', '14', '1');
INSERT INTO `sys_role_dept` VALUES ('16', '1', '10');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('1', '24');
INSERT INTO `sys_role_menu` VALUES ('1', '25');
INSERT INTO `sys_role_menu` VALUES ('1', '28');
INSERT INTO `sys_role_menu` VALUES ('1', '31');
INSERT INTO `sys_role_menu` VALUES ('1', '32');
INSERT INTO `sys_role_menu` VALUES ('1', '33');
INSERT INTO `sys_role_menu` VALUES ('1', '34');
INSERT INTO `sys_role_menu` VALUES ('1', '41');
INSERT INTO `sys_role_menu` VALUES ('1', '42');
INSERT INTO `sys_role_menu` VALUES ('1', '43');
INSERT INTO `sys_role_menu` VALUES ('1', '44');
INSERT INTO `sys_role_menu` VALUES ('1', '45');
INSERT INTO `sys_role_menu` VALUES ('1', '51');
INSERT INTO `sys_role_menu` VALUES ('1', '52');
INSERT INTO `sys_role_menu` VALUES ('1', '61');
INSERT INTO `sys_role_menu` VALUES ('1', '62');
INSERT INTO `sys_role_menu` VALUES ('1', '63');
INSERT INTO `sys_role_menu` VALUES ('1', '64');
INSERT INTO `sys_role_menu` VALUES ('1', '71');
INSERT INTO `sys_role_menu` VALUES ('1', '72');
INSERT INTO `sys_role_menu` VALUES ('1', '73');
INSERT INTO `sys_role_menu` VALUES ('1', '74');
INSERT INTO `sys_role_menu` VALUES ('1', '100');
INSERT INTO `sys_role_menu` VALUES ('1', '101');
INSERT INTO `sys_role_menu` VALUES ('1', '102');
INSERT INTO `sys_role_menu` VALUES ('1', '103');
INSERT INTO `sys_role_menu` VALUES ('1', '104');
INSERT INTO `sys_role_menu` VALUES ('1', '110');
INSERT INTO `sys_role_menu` VALUES ('1', '111');
INSERT INTO `sys_role_menu` VALUES ('1', '112');
INSERT INTO `sys_role_menu` VALUES ('1', '113');
INSERT INTO `sys_role_menu` VALUES ('1', '114');
INSERT INTO `sys_role_menu` VALUES ('1', '251');
INSERT INTO `sys_role_menu` VALUES ('1', '252');
INSERT INTO `sys_role_menu` VALUES ('1', '253');
INSERT INTO `sys_role_menu` VALUES ('1', '254');
INSERT INTO `sys_role_menu` VALUES ('14', '1');
INSERT INTO `sys_role_menu` VALUES ('14', '2');
INSERT INTO `sys_role_menu` VALUES ('14', '3');
INSERT INTO `sys_role_menu` VALUES ('14', '4');
INSERT INTO `sys_role_menu` VALUES ('14', '5');
INSERT INTO `sys_role_menu` VALUES ('14', '6');
INSERT INTO `sys_role_menu` VALUES ('14', '7');
INSERT INTO `sys_role_menu` VALUES ('14', '8');
INSERT INTO `sys_role_menu` VALUES ('14', '14');
INSERT INTO `sys_role_menu` VALUES ('14', '21');
INSERT INTO `sys_role_menu` VALUES ('14', '31');
INSERT INTO `sys_role_menu` VALUES ('14', '41');
INSERT INTO `sys_role_menu` VALUES ('14', '51');
INSERT INTO `sys_role_menu` VALUES ('14', '61');
INSERT INTO `sys_role_menu` VALUES ('14', '71');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '简介',
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `label` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0-正常，1-删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_idx1_username` (`username`) USING BTREE,
  UNIQUE KEY `user_idx2_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$vg5QNHhCknAqevx9vM2s5esllJEzF/pa8VZXtFYHhhOhUcCw/GWyS', null, '17034642111', null, 'admin', '10', '2018-04-20 07:15:18', '2019-02-12 16:17:10', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_zuul_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_zuul_route`;
CREATE TABLE `sys_zuul_route` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'router Id',
  `path` varchar(255) NOT NULL COMMENT '路由路径',
  `service_id` varchar(255) NOT NULL COMMENT '服务名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'url代理',
  `strip_prefix` char(1) DEFAULT '1' COMMENT '转发去掉前缀',
  `retryable` char(1) DEFAULT '1' COMMENT '是否重试',
  `enabled` char(1) DEFAULT '1' COMMENT '是否启用',
  `sensitiveHeaders_list` varchar(255) DEFAULT NULL COMMENT '敏感请求头',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='动态路由配置表';

-- ----------------------------
-- Records of sys_zuul_route
-- ----------------------------
INSERT INTO `sys_zuul_route` VALUES ('3', '/taroco-admin/**', 'taroco-admin', '', '1', '1', '1', '', '2018-05-17 14:09:06', '2018-08-02 08:31:06', '0');
INSERT INTO `sys_zuul_route` VALUES ('4', '/admin/**', 'taroco-rbac-service', '', '1', '1', '1', '', '2018-05-21 11:40:38', '2018-08-20 17:36:08', '0');
INSERT INTO `sys_zuul_route` VALUES ('5', '/auth/**', 'taroco-authentication-server', '', '1', '1', '1', '', '2018-05-21 11:41:08', '2018-08-02 08:31:34', '0');
INSERT INTO `sys_zuul_route` VALUES ('6', '/taroco-registry/**', 'taroco-registry', '', '1', '1', '1', '', '2018-05-21 11:41:08', '2018-08-02 08:32:09', '0');
INSERT INTO `sys_zuul_route` VALUES ('7', '/taroco-monitor/**', 'taroco-monitor', '', '1', '1', '1', '', '2018-05-21 11:41:08', '2018-08-02 08:32:09', '0');
INSERT INTO `sys_zuul_route` VALUES ('8', '/taroco-config/**', 'taroco-config', null, '1', '1', '1', null, '2018-08-06 03:36:21', '2018-08-06 11:36:16', '0');
INSERT INTO `sys_zuul_route` VALUES ('9', '/taroco-demo1/**', 'taroco-demo1', '', '1', '1', '1', '', '2018-08-10 02:29:13', '2018-08-10 14:24:44', '1');
