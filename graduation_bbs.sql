/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50096
 Source Host           : localhost:3306
 Source Schema         : graduation_bbs

 Target Server Type    : MySQL
 Target Server Version : 50096
 File Encoding         : 65001

 Date: 29/11/2021 11:36:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods_oversold_record
-- ----------------------------
DROP TABLE IF EXISTS `goods_oversold_record`;
CREATE TABLE `goods_oversold_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `spu_id` bigint(20) NOT NULL COMMENT '商品spuId',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '商品skuId',
  `subtract_stock_num` int(11) NOT NULL COMMENT '库存扣减数量',
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE INDEX `subtractUnique` USING BTREE(`order_id`, `spu_id`, `sku_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品超卖记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods_oversold_record
-- ----------------------------
INSERT INTO `goods_oversold_record` VALUES (1, 1, 1, NULL, 1);
INSERT INTO `goods_oversold_record` VALUES (2, 1, 1, NULL, 1);
INSERT INTO `goods_oversold_record` VALUES (3, 1, 1, NULL, 1);
INSERT INTO `goods_oversold_record` VALUES (4, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for p_comment
-- ----------------------------
DROP TABLE IF EXISTS `p_comment`;
CREATE TABLE `p_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` int(11) NOT NULL COMMENT '评论者id',
  `post_id` int(11) NOT NULL COMMENT '所属帖子id',
  `comment` varchar(10000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of p_comment
-- ----------------------------
INSERT INTO `p_comment` VALUES (27, 20, 63, '回复测试1', '2020-04-29 16:01:34');
INSERT INTO `p_comment` VALUES (28, 20, 63, '回复测试2', '2020-04-29 16:01:48');
INSERT INTO `p_comment` VALUES (29, 20, 81, '回复测试', '2020-04-29 16:14:39');
INSERT INTO `p_comment` VALUES (30, 38, 78, '回复测试', '2020-04-29 16:15:33');
INSERT INTO `p_comment` VALUES (31, 38, 57, '回复测试', '2020-04-29 16:16:05');
INSERT INTO `p_comment` VALUES (32, 20, 82, '评论测试face[嘻嘻] ', '2020-04-29 17:53:49');
INSERT INTO `p_comment` VALUES (33, 33, 63, '回复测试3', '2020-05-08 18:31:58');
INSERT INTO `p_comment` VALUES (39, 38, 82, '评论测试2', '2020-05-09 15:44:39');
INSERT INTO `p_comment` VALUES (40, 38, 82, '评论测试3', '2020-05-09 15:44:45');
INSERT INTO `p_comment` VALUES (46, 20, 67, '测试', '2020-05-11 11:27:50');
INSERT INTO `p_comment` VALUES (51, 33, 67, 'pinglun', '2020-05-11 11:40:29');
INSERT INTO `p_comment` VALUES (52, 33, 67, '1', '2020-05-11 11:40:39');
INSERT INTO `p_comment` VALUES (53, 33, 67, '2', '2020-05-11 11:40:41');
INSERT INTO `p_comment` VALUES (54, 33, 67, '3', '2020-05-11 11:40:44');
INSERT INTO `p_comment` VALUES (55, 33, 67, '4', '2020-05-11 11:40:47');
INSERT INTO `p_comment` VALUES (56, 33, 67, '5', '2020-05-11 11:40:49');
INSERT INTO `p_comment` VALUES (57, 33, 67, '6', '2020-05-11 11:40:52');
INSERT INTO `p_comment` VALUES (58, 33, 67, '7', '2020-05-11 11:40:55');
INSERT INTO `p_comment` VALUES (59, 33, 67, '8', '2020-05-11 11:40:57');
INSERT INTO `p_comment` VALUES (60, 33, 67, '9', '2020-05-11 11:41:00');
INSERT INTO `p_comment` VALUES (61, 33, 67, '10', '2020-05-11 11:41:03');
INSERT INTO `p_comment` VALUES (62, 38, 82, '1', '2020-05-11 11:52:34');
INSERT INTO `p_comment` VALUES (63, 38, 82, '2', '2020-05-11 11:52:37');

-- ----------------------------
-- Table structure for p_post
-- ----------------------------
DROP TABLE IF EXISTS `p_post`;
CREATE TABLE `p_post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `content` varchar(10000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `post_type_id` int(11) NOT NULL COMMENT '帖子类型id',
  `recommend` int(2) NOT NULL DEFAULT 0 COMMENT '是否为推荐（0-否，1-是）',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '状态（0-正常，1-封禁）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `add_recommend_time` datetime NULL DEFAULT NULL COMMENT '添加推荐时间',
  `add_banned_time` datetime NULL DEFAULT NULL COMMENT '帖子封禁时间',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '帖子表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of p_post
-- ----------------------------
INSERT INTO `p_post` VALUES (56, '分享测试2', '分享测试2', 20, 4, 1, 0, '2020-04-29 15:58:46', NULL, '2020-04-29 16:13:17', NULL);
INSERT INTO `p_post` VALUES (57, '分享测试3', '分享测试3', 20, 4, 1, 0, '2020-04-29 15:59:05', NULL, '2020-04-29 16:13:40', NULL);
INSERT INTO `p_post` VALUES (59, '问答测试2', '问答测试2', 20, 5, 0, 0, '2020-04-29 16:00:10', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (60, '问答测试3', '问答测试3', 20, 5, 0, 0, '2020-04-29 16:00:23', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (61, '讨论测试1', '讨论测试1', 20, 6, 0, 0, '2020-04-29 16:00:42', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (62, '讨论测试2', '讨论测试2', 20, 6, 0, 0, '2020-04-29 16:00:54', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (63, '讨论测试3', '讨论测试3', 20, 6, 0, 0, '2020-04-29 16:01:07', NULL, NULL, '2020-05-09 16:32:25');
INSERT INTO `p_post` VALUES (64, '分享测试4', '分享测试4', 33, 4, 0, 0, '2020-04-29 16:02:24', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (65, '分享测试5', '分享测试5', 33, 4, 1, 0, '2020-04-29 16:02:36', NULL, '2020-04-29 16:13:35', NULL);
INSERT INTO `p_post` VALUES (66, '分享测试6', '分享测试6', 33, 4, 0, 0, '2020-04-29 16:02:47', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (67, '问答测试4', '问答测试4', 33, 5, 1, 0, '2020-04-29 16:03:08', NULL, '2020-04-29 16:13:58', NULL);
INSERT INTO `p_post` VALUES (68, '问答测试5', '问答测试5', 33, 5, 0, 0, '2020-04-29 16:03:21', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (73, '分享测试7', '分享测试7', 38, 4, 0, 0, '2020-04-29 16:11:02', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (74, '分享测试8', '分享测试8', 38, 4, 0, 0, '2020-04-29 16:11:12', NULL, '2020-04-29 16:13:23', NULL);
INSERT INTO `p_post` VALUES (75, '分享测试9', '分享测试9', 38, 4, 1, 0, '2020-04-29 16:11:23', NULL, '2020-04-29 16:13:31', NULL);
INSERT INTO `p_post` VALUES (76, '问答测试7', '问答测试7', 38, 5, 0, 0, '2020-04-29 16:11:45', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (77, '问答测试8', '问答测试8', 38, 5, 0, 0, '2020-04-29 16:11:59', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (78, '问答测试9', '问答测试9', 38, 5, 1, 0, '2020-04-29 16:12:10', NULL, '2020-04-29 16:13:49', NULL);
INSERT INTO `p_post` VALUES (79, '讨论测试7', '讨论测试7', 38, 6, 1, 0, '2020-04-29 16:12:22', NULL, '2020-04-29 16:13:29', NULL);
INSERT INTO `p_post` VALUES (80, '讨论测试8', '讨论测试8', 38, 6, 0, 0, '2020-04-29 16:12:32', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (81, '讨论测试9', '讨论测试9', 38, 6, 1, 0, '2020-04-29 16:12:41', NULL, '2020-04-29 16:13:26', NULL);
INSERT INTO `p_post` VALUES (82, '测试', '一个测试帖子\nface[哈哈] \n[hr]\n[pre]\n可以填写代码或任意文本\n[/pre]img[http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/postPicture/2020-04-29/736636f3-e103-43d6-9a6d-05f4799c5c46laojun.jpg] ', 20, 4, 0, 0, '2020-04-29 17:53:04', NULL, NULL, NULL);
INSERT INTO `p_post` VALUES (83, '测试', 'face[微笑] \n测试\nimg[http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/postPicture/2020-05-10/ac707dfd-d40f-4b2b-8391-19aecfb58ffctouxiang.jpg] ', 38, 4, 0, 0, '2020-05-10 16:30:31', NULL, '2020-05-10 16:59:25', NULL);
INSERT INTO `p_post` VALUES (84, '测试', 'face[微笑] \n测试\nimg[http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/postPicture/2020-05-10/388f2ea3-6cbc-4eb4-9fc9-15f7c7d6d1d9touxiang.jpg] ', 38, 4, 1, 1, '2020-05-10 16:56:21', NULL, '2020-05-10 16:58:56', '2020-05-10 16:59:52');

-- ----------------------------
-- Table structure for p_post_type
-- ----------------------------
DROP TABLE IF EXISTS `p_post_type`;
CREATE TABLE `p_post_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子类型id',
  `post_type` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子类型',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '帖子类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of p_post_type
-- ----------------------------
INSERT INTO `p_post_type` VALUES (4, '分享');
INSERT INTO `p_post_type` VALUES (5, '问答');
INSERT INTO `p_post_type` VALUES (6, '讨论');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL COMMENT '角色id',
  `role_name` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_desc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_USER', '普通成员');
INSERT INTO `sys_role` VALUES (2, 'ROLE_ADMIN', '管理员');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `gender` int(2) NOT NULL COMMENT '性别(0男、1女)',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(2) NOT NULL DEFAULT 0 COMMENT '封禁标记（0-正常，1-封禁）',
  `add_banned_time` datetime NULL DEFAULT NULL COMMENT '用户封禁时间',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (20, 'zhang', '$2a$10$SecrFfgeFsh3P//b0cOlAe6fqshn4ssnJlfy.RtcjtXEjI766A0Yu', 'silence', 0, 'http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/touxiang/Df5BGiTHdt.gif', '1520949225@qq.com', '2020-03-16 18:20:56', '2020-05-12 11:29:58', 0, '2020-04-11 23:20:50');
INSERT INTO `sys_user` VALUES (21, 'wang', '$2a$10$OKiEv3i80W9yIekzNIue5eLML/rrVsILk9ofqM8TFS04fysT8NGxa', 'wang', 0, 'http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/touxiang/GtCMcPEnQ5.gif', '123@qq.com', '2020-03-27 14:20:57', '2020-04-03 14:26:56', 0, '2020-04-11 23:20:48');
INSERT INTO `sys_user` VALUES (22, '111', '$2a$10$bD.Vd77pT8IN2ldqrXinyOIAZfy7uUZUoNYQA.NDZIS.7Qx5fSKiG', 'silence', 0, '/admin/images/upAvatar.jpg', '123@qq.com', '2020-04-07 10:29:17', NULL, 0, '2020-04-11 23:38:27');
INSERT INTO `sys_user` VALUES (24, '333', '$2a$10$UGUYH0xSKcjP49hEp204C.RHQtGHXN6ZcM1R8aipntdC3uLbEe1wy', 'wang', 0, '/admin/images/upAvatar.jpg', '123@qq.com', '2020-04-07 10:30:12', NULL, 0, '2020-04-11 23:39:22');
INSERT INTO `sys_user` VALUES (27, '666', '$2a$10$UGUYH0xSKcjP49hEp204C.RHQtGHXN6ZcM1R8aipntdC3uLbEe1wy', 'wang', 0, '/admin/images/upAvatar.jpg', '123@qq.com', '2020-04-07 10:30:12', NULL, 0, '2020-04-10 22:55:02');
INSERT INTO `sys_user` VALUES (28, '777', '$2a$10$UGUYH0xSKcjP49hEp204C.RHQtGHXN6ZcM1R8aipntdC3uLbEe1wy', 'wang', 0, '/admin/images/upAvatar.jpg', '123@qq.com', '2020-04-07 10:30:12', NULL, 0, '2020-04-10 23:07:22');
INSERT INTO `sys_user` VALUES (29, '888', '$2a$10$UGUYH0xSKcjP49hEp204C.RHQtGHXN6ZcM1R8aipntdC3uLbEe1wy', 'wang', 0, '/admin/images/upAvatar.jpg', '123@qq.com', '2020-04-07 10:30:12', NULL, 0, '2020-04-10 22:55:22');
INSERT INTO `sys_user` VALUES (30, '999', '$2a$10$UGUYH0xSKcjP49hEp204C.RHQtGHXN6ZcM1R8aipntdC3uLbEe1wy', 'wang', 0, '/admin/images/upAvatar.jpg', '123@qq.com', '2020-04-07 10:30:12', NULL, 0, '2020-04-11 23:39:16');
INSERT INTO `sys_user` VALUES (32, '1111', '$2a$10$UGUYH0xSKcjP49hEp204C.RHQtGHXN6ZcM1R8aipntdC3uLbEe1wy', 'wang', 0, '/admin/images/upAvatar.jpg', '123@qq.com', '2020-04-07 10:30:12', NULL, 0, '2020-04-10 22:54:59');
INSERT INTO `sys_user` VALUES (33, 'haha', '$2a$10$eYC9DVhELgxrKbJdT4LEhuJc1SUgecRdyy/tXhbHJ5F5nlpxhWoEm', 'haha', 1, 'http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/touxiang/c4e7eBPZKC.jpg', '1520949225@qq.com', '2020-04-09 14:05:19', '2020-04-23 14:56:34', 0, '2020-04-13 09:50:38');
INSERT INTO `sys_user` VALUES (34, 'wangzh', '$2a$10$x1hdQP9mTXLlRlXXJviy1OZWC3wu5HqqGuLgsVd8qEVbZ5q7bTSn2', 'zhang1111', 0, NULL, '1520949225@qq.com', '2020-04-14 18:08:18', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (35, 'zhangsan', '$2a$10$moCkMFH4ySBj9EOW9vzi7OrH6oFh8bCnNXm8JV.huj2o3IGi9PF3K', '1', 0, NULL, '1520949225@qq.com', '2020-04-14 18:12:42', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (36, 'lisi', '$2a$10$kgAGTrrtt9oGqkXQKuOjW.BUckd4lIDfwEQUDOrBOGlt9WvOZ1gUu', 'zhang', 0, '/admin/images/moren.jpg', '1520949225@qq.com', '2020-04-14 18:21:46', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (37, 'wwwww', '$2a$10$mAZ4Zh8WAE94GEpiBKP7YO04BN1kGZZi8UUXBWoynWJdEg0bNSqEy', 'sdfd', 0, '/admin/images/upAvatar.jpg', '1520949225@qq.com', '2020-04-14 18:34:41', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (38, 'xixi', '$2a$10$0PYr6SUCr3OIZO3lku06e.uRJyB3Fthgk6lrR3KP0B135DY5FbUcS', 'wukong', 0, 'http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/touxiang/wEywr4Y7dR.jpg', '1520949225@qq.com', '2020-04-14 18:39:30', '2020-05-12 11:44:01', 0, NULL);
INSERT INTO `sys_user` VALUES (39, 'hahaaaa', '$2a$10$qs2Gny3ZbC3D13Hm4.O3L.RaiwZiph40UhRR8mL71E8Uei8.hIChS', '123', 1, '/admin/images/moren.jpg', '1520949225@qq.com', '2020-04-15 09:08:48', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (40, 'aaaa', '$2a$10$OL/XV4WN2bOnAzWaNL7B9.xXAKbyYkbJGqZiev42pi8DqTpcnbAt2', '123', 1, '/admin/images/moren.jpg', '123@qq.com', '2020-04-15 09:21:49', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (41, 'aaaaa', '$2a$10$nIGkec7pzRVbBwnvuKrezuir82IjgcQ.pFGdUzRNToDsiSFk/sHM2', '123', 1, '/admin/images/moren.jpg', '123@qq.com', '2020-04-15 09:24:11', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (42, 'aaaaaa', '$2a$10$zwE.HNe7jW0oXu8wDKjgceBROZJLrZ7Sh.bZJGUsH0TOtMHQoFhmu', '123', 1, '/admin/images/moren.jpg', '123@qq.com', '2020-04-15 09:28:46', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (43, 'zhang111', '$2a$10$I.pernMChm4DCo5YO3uBI.g8GUHsn0jmMyHHqWyOAEqkpTGo2Dqkq', 'zhangdasvsavsdv', 0, '/admin/images/moren.jpg', '1520949225@qq.com', '2020-04-15 09:44:38', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (44, 'zhang222', '$2a$10$vAG7Pmh5PN4JowaL8j5jMOOcIj.4WwaKq8pIp0pp1B0Dqxk0Gm0pW', 'zhang', 0, 'http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/touxiang/b26YM8MBcF.png', '1520949225@qq.com', '2020-04-15 09:45:23', '2020-04-15 10:12:38', 1, '2020-04-23 14:22:54');
INSERT INTO `sys_user` VALUES (45, 'zrttu', '$2a$10$OSrYyB6/CbYDgPwahHDJeu.4qLpE9CQ1cpX44yFwXmg.JsprZM5j.', 'li', 0, 'http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/touxiang/symMy2yKJ3.png', '1520949225@qq.com', '2020-04-21 12:21:25', NULL, 1, '2020-04-23 14:22:52');
INSERT INTO `sys_user` VALUES (46, 'enen', '$2a$10$BQqZv.m/rKD60TPrQsifoeR4D7EnkwrlncQhAs.wWnX2u32swSnmq', 'ena', 1, 'http://zybsilence.oss-cn-shenzhen.aliyuncs.com/silence/touxiang/BRhx6Trrkr.jpg', '1520949225@qq.com', '2020-04-29 16:10:18', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (47, 'z123456', '$2a$10$PFTMWLDU4MZROGZHbeFIlOSXg4qsDCAPpoFXL6MJZiU0T2J3VJ5eG', 'aaa', 0, '/admin/images/moren.jpg', '1520949225@qq.com', '2020-05-11 16:45:59', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (48, 'zhang123', '$2a$10$iJnJbOiVvST0lN3XbkvJZOzeS5gfFJ/xzvcTOgs0LAx/i07Wp6tfW', '1', 0, '/admin/images/moren.jpg', '1520949225@qq.com', '2020-05-12 18:04:33', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (49, 'zhang223', '$2a$10$AXAZnenWGElucwB7Bppt8unMr1k62D4XUhoaeomF/RgRFScAJDgxa', '1', 0, '/admin/images/moren.jpg', '1520949225@qq.com', '2020-05-12 19:51:34', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (50, 'zhang234', '$2a$10$YhE/01WqVf5HvjyBTO7XWuf6MZpPFZELwGwGBxJzk3ckZfuslGwWe', 'zhang', 0, '/admin/images/moren.jpg', '1520949225@qq.com', '2020-05-12 20:23:12', NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户-角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (5, 20, 2);
INSERT INTO `sys_user_role` VALUES (6, 21, 1);
INSERT INTO `sys_user_role` VALUES (7, 22, 1);
INSERT INTO `sys_user_role` VALUES (9, 24, 1);
INSERT INTO `sys_user_role` VALUES (12, 27, 1);
INSERT INTO `sys_user_role` VALUES (13, 28, 1);
INSERT INTO `sys_user_role` VALUES (14, 29, 1);
INSERT INTO `sys_user_role` VALUES (15, 30, 1);
INSERT INTO `sys_user_role` VALUES (17, 32, 1);
INSERT INTO `sys_user_role` VALUES (18, 33, 1);
INSERT INTO `sys_user_role` VALUES (19, 34, 1);
INSERT INTO `sys_user_role` VALUES (20, 35, 1);
INSERT INTO `sys_user_role` VALUES (21, 36, 1);
INSERT INTO `sys_user_role` VALUES (22, 37, 1);
INSERT INTO `sys_user_role` VALUES (23, 38, 1);
INSERT INTO `sys_user_role` VALUES (24, 39, 1);
INSERT INTO `sys_user_role` VALUES (25, 40, 1);
INSERT INTO `sys_user_role` VALUES (26, 41, 1);
INSERT INTO `sys_user_role` VALUES (27, 42, 1);
INSERT INTO `sys_user_role` VALUES (28, 43, 1);
INSERT INTO `sys_user_role` VALUES (29, 44, 1);
INSERT INTO `sys_user_role` VALUES (30, 45, 1);
INSERT INTO `sys_user_role` VALUES (31, 46, 1);
INSERT INTO `sys_user_role` VALUES (32, 47, 1);
INSERT INTO `sys_user_role` VALUES (33, 48, 1);
INSERT INTO `sys_user_role` VALUES (34, 49, 1);
INSERT INTO `sys_user_role` VALUES (35, 50, 1);

-- ----------------------------
-- Table structure for test_table
-- ----------------------------
DROP TABLE IF EXISTS `test_table`;
CREATE TABLE `test_table`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`),
  INDEX `number_index` USING BTREE(`number`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test_table
-- ----------------------------
INSERT INTO `test_table` VALUES (1, 1);
INSERT INTO `test_table` VALUES (2, 3);
INSERT INTO `test_table` VALUES (3, 7);
INSERT INTO `test_table` VALUES (4, 8);
INSERT INTO `test_table` VALUES (5, 12);

-- ----------------------------
-- Table structure for user_comment_tags
-- ----------------------------
DROP TABLE IF EXISTS `user_comment_tags`;
CREATE TABLE `user_comment_tags`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-评论关联id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `comment_id` int(11) NOT NULL COMMENT '评论id',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论点赞表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_comment_tags
-- ----------------------------
INSERT INTO `user_comment_tags` VALUES (2, 20, 60);
INSERT INTO `user_comment_tags` VALUES (3, 20, 57);
INSERT INTO `user_comment_tags` VALUES (5, 20, 61);
INSERT INTO `user_comment_tags` VALUES (7, 20, 32);

-- ----------------------------
-- Table structure for user_post_collect
-- ----------------------------
DROP TABLE IF EXISTS `user_post_collect`;
CREATE TABLE `user_post_collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-帖子关联id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `post_id` int(11) NOT NULL COMMENT '帖子id',
  `create_time` datetime NOT NULL COMMENT '添加收藏时间',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_post_collect
-- ----------------------------
INSERT INTO `user_post_collect` VALUES (4, 20, 83, '2020-05-11 11:20:39');
INSERT INTO `user_post_collect` VALUES (11, 20, 67, '2020-05-11 11:23:21');
INSERT INTO `user_post_collect` VALUES (12, 20, 82, '2020-05-11 11:23:37');
INSERT INTO `user_post_collect` VALUES (13, 33, 67, '2020-05-11 11:38:13');

-- ----------------------------
-- Table structure for user_post_tags
-- ----------------------------
DROP TABLE IF EXISTS `user_post_tags`;
CREATE TABLE `user_post_tags`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-帖子关联id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `post_id` int(11) NOT NULL COMMENT '帖子id',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '点赞表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_post_tags
-- ----------------------------
INSERT INTO `user_post_tags` VALUES (1, 20, 82);
INSERT INTO `user_post_tags` VALUES (4, 20, 83);
INSERT INTO `user_post_tags` VALUES (6, 20, 67);

SET FOREIGN_KEY_CHECKS = 1;
