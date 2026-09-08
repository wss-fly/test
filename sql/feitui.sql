/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80046 (8.0.46)
 Source Host           : localhost:3306
 Source Schema         : feitui

 Target Server Type    : MySQL
 Target Server Version : 80046 (8.0.46)
 File Encoding         : 65001

 Date: 08/09/2026 18:12:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码(MD5加密)',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'ADMIN' COMMENT '角色',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台管理员账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES (1, 'admin', '0192023a7bbd73250516f069df18b500', '超级管理员', 'SUPER', 1, '2026-09-07 16:53:33', '2026-08-28 13:41:58', '2026-09-07 16:53:33');
INSERT INTO `t_admin_user` VALUES (2, 'demo', 'e10adc3949ba59abbe56e057f20f883e', 'demo', 'ADMIN', 1, '2026-09-07 16:52:04', '2026-09-04 18:05:24', '2026-09-07 16:52:04');
INSERT INTO `t_admin_user` VALUES (3, 'demo1', 'e10adc3949ba59abbe56e057f20f883e', 'demo', 'ADMIN', 1, NULL, '2026-09-04 18:07:13', '2026-09-04 18:07:17');

-- ----------------------------
-- Table structure for t_contact
-- ----------------------------
DROP TABLE IF EXISTS `t_contact`;
CREATE TABLE `t_contact`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号/WhatsApp',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `company` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司名称',
  `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'Facebook' COMMENT '目标平台',
  `requirement` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '需求描述',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态 0-待处理 1-已处理 2-已关闭',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  INDEX `idx_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户联系咨询表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_contact
-- ----------------------------
INSERT INTO `t_contact` VALUES (3, 'ws', '19115289951', '3493068572@qq.com', '', 'Facebook', '我对你们的网站比较感兴趣，了解下', 1, NULL, '2026-08-28 15:58:45', '2026-08-28 15:59:07');
INSERT INTO `t_contact` VALUES (4, '彭晓珍', '19115889953', '', '', 'Facebook', '', 1, NULL, '2026-08-28 16:08:46', '2026-08-31 13:45:50');
INSERT INTO `t_contact` VALUES (5, '小王', '13219682596', '', '', 'Facebook', '希望实现facebook平台批量发帖的功能。', 1, NULL, '2026-08-31 13:45:35', '2026-09-03 11:05:38');
INSERT INTO `t_contact` VALUES (7, '小彭', '1548488888', '3493068572@qq.com', '', 'Facebook', '我想要了解下你们的软件', 0, NULL, '2026-09-03 13:31:28', '2026-09-03 13:31:28');
INSERT INTO `t_contact` VALUES (8, '小于', '19115289951', '', '', 'Facebook', '超级无敌大帅比1234', 0, NULL, '2026-09-04 17:43:17', '2026-09-04 17:43:17');

-- ----------------------------
-- Table structure for t_custom_service
-- ----------------------------
DROP TABLE IF EXISTS `t_custom_service`;
CREATE TABLE `t_custom_service`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '服务内容',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定制开发服务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_custom_service
-- ----------------------------

-- ----------------------------
-- Table structure for t_feature
-- ----------------------------
DROP TABLE IF EXISTS `t_feature`;
CREATE TABLE `t_feature`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能标题',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能描述',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `tags` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签JSON数组',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '功能模块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_feature
-- ----------------------------
INSERT INTO `t_feature` VALUES (7, '批量发帖', '多账号定时批量发帖，实现高频曝光与矩阵式分发，支持素材轮换与智能去重', '📝', '[\"多账号管理\", \"定时发布\", \"素材轮换\"]', 1, 1, '2026-08-25 18:18:42', '2026-08-25 18:18:42');
INSERT INTO `t_feature` VALUES (8, '一键四连', '一键批量完成点赞、评论、分享、转发、收藏等操作，大幅提升互动热度', '👆', '[\"批量点赞\", \"批量评论\", \"批量分享\", \"批量收藏\"]', 2, 1, '2026-08-25 18:18:42', '2026-08-25 18:18:42');
INSERT INTO `t_feature` VALUES (9, '评论操作', '批量点赞评论、回复评论，提升帖子互动率与权重，增强用户粘性', '💬', '[\"点赞评论\", \"回复评论\", \"批量操作\"]', 3, 1, '2026-08-25 18:18:42', '2026-08-25 18:18:42');
INSERT INTO `t_feature` VALUES (10, '用户操作', '批量关注用户、私信好友、添加好友等功能，快速积累社交关系网络', '👤', '[\"批量关注\", \"批量私信\", \"批量加好友\"]', 4, 1, '2026-08-25 18:18:42', '2026-08-25 18:18:42');
INSERT INTO `t_feature` VALUES (11, '小组操作', '加入小组、分享小组、在小组中发帖、在小组中加好友，全面覆盖小组营销', '👥', '[\"加入小组\", \"小组发帖\", \"小组互动\"]', 5, 1, '2026-08-25 18:18:42', '2026-08-25 18:18:42');
INSERT INTO `t_feature` VALUES (12, '采集功能', '采集帖子评论、点赞用户、用户好友、用户帖子、小组成员、小组帖子等数据', '🔍', '[\"采集评论\", \"采集用户\", \"采集帖子\"]', 6, 1, '2026-08-25 18:18:42', '2026-08-25 18:18:42');
INSERT INTO `t_feature` VALUES (13, '搜索功能', '搜索用户、搜索帖子、搜索Reels、搜索小组、搜索小组帖子等', '🔎', '[\"搜索用户\", \"搜索帖子\", \"搜索Reels\"]', 7, 1, '2026-08-25 18:18:42', '2026-08-25 18:18:42');

-- ----------------------------
-- Table structure for t_feature_highlight
-- ----------------------------
DROP TABLE IF EXISTS `t_feature_highlight`;
CREATE TABLE `t_feature_highlight`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '亮点名称',
  `color` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#00d4ff' COMMENT '显示颜色',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '功能亮点标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_feature_highlight
-- ----------------------------
INSERT INTO `t_feature_highlight` VALUES (13, '批量发帖', '#00d4ff', 1, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (14, '一键四连', '#a855f7', 2, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (15, '评论操作', '#22c55e', 3, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (16, '用户操作', '#3b82f6', 4, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (17, '小组操作', '#f97316', 5, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (18, '采集功能', '#ec4899', 6, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (19, '搜索功能', '#f43f5e', 7, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (20, '多线程操作', '#06b6d4', 8, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (21, '实时监控', '#10b981', 9, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (22, '智能筛选', '#6366f1', 10, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (23, '行为模拟', '#eab308', 11, 1, '2026-08-25 18:18:42');
INSERT INTO `t_feature_highlight` VALUES (24, '安全稳定', '#8b5cf6', 12, 1, '2026-08-25 18:18:42');

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资讯标题',
  `summary` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '摘要',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '正文内容',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类',
  `cover_image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `views` int NULL DEFAULT 0 COMMENT '浏览量',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
  `publish_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category`(`category` ASC) USING BTREE,
  INDEX `idx_publish_time`(`publish_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '新闻资讯表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_news
-- ----------------------------

-- ----------------------------
-- Table structure for t_stats
-- ----------------------------
DROP TABLE IF EXISTS `t_stats`;
CREATE TABLE `t_stats`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `stat_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '统计键',
  `stat_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '统计值',
  `stat_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '统计标签',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_stat_key`(`stat_key` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '统计数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_stats
-- ----------------------------
INSERT INTO `t_stats` VALUES (1, 'exposure_days', '1-3天', '曝光提升', '快速实现引流曝光显著提升', 1, '2026-08-25 17:48:04', '2026-08-25 17:48:04');
INSERT INTO `t_stats` VALUES (2, 'growth_days', '7天', '涨粉增长', '7天内完成涨粉持续增长', 2, '2026-08-25 17:48:04', '2026-08-25 17:48:04');
INSERT INTO `t_stats` VALUES (3, 'cost_save', '90%', '人工成本节省', '大幅节省人工运营成本', 3, '2026-08-25 17:48:04', '2026-08-25 17:48:04');
INSERT INTO `t_stats` VALUES (4, 'precision_users', '200+', '每日精准用户', '每日获取大量精准用户', 4, '2026-08-25 17:48:04', '2026-08-25 17:48:04');

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频标题',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频描述',
  `video_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频URL',
  `cover_image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图',
  `duration` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '时长',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频演示表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_video
-- ----------------------------

-- ----------------------------
-- Table structure for t_visit
-- ----------------------------
DROP TABLE IF EXISTS `t_visit`;
CREATE TABLE `t_visit`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `visitor_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访客唯一ID(浏览器localStorage)',
  `session_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会话ID',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访客IP',
  `referrer` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源页面URL',
  `source` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'utm_source或来源标记',
  `channel` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '渠道分类(Facebook/搜索引擎/直接访问等)',
  `device` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备类型(mobile/tablet/desktop)',
  `browser` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
  `landing_page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '落地页面',
  `first_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '会话开始时间',
  `last_active` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近活跃时间(用于在线判定)',
  `pv` int NULL DEFAULT 1 COMMENT '本会话浏览量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_session`(`session_id` ASC) USING BTREE,
  INDEX `idx_channel`(`channel` ASC) USING BTREE,
  INDEX `idx_last_active`(`last_active` ASC) USING BTREE,
  INDEX `idx_first_time`(`first_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '访问会话表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_visit
-- ----------------------------
INSERT INTO `t_visit` VALUES (1, 'test_visitor_1', 'test_session_1', '127.0.0.1', 'https://www.facebook.com/abc', 'facebook', 'Facebook', 'desktop', 'Chrome', 'Windows', '/', '2026-08-28 13:53:01', '2026-08-28 13:53:01', 1);
INSERT INTO `t_visit` VALUES (2, 'visitor_f21e1d2fb72a4171a0d5', 'session_b6fd0511a29349db9060', '127.0.0.1', 'http://localhost:5173/contact', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 13:53:28', '2026-08-28 14:07:25', 1);
INSERT INTO `t_visit` VALUES (3, 'visitor_f21e1d2fb72a4171a0d5', 'session_9eb1a18ccbc7485ca734', '127.0.0.1', 'http://localhost:5173/contact', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/contact', '2026-08-28 14:04:05', '2026-08-28 14:07:25', 2);
INSERT INTO `t_visit` VALUES (4, 'visitor_f21e1d2fb72a4171a0d5', 'session_af34d4a84f214cc5b0a7', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 14:07:29', '2026-08-28 14:07:29', 2);
INSERT INTO `t_visit` VALUES (5, 'visitor_f21e1d2fb72a4171a0d5', 'session_4cb2c3a999544ff689ef', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 14:07:30', '2026-08-28 16:04:25', 57);
INSERT INTO `t_visit` VALUES (6, 'visitor_f21e1d2fb72a4171a0d5', 'session_e30b85231c8b416abc12', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 15:21:22', '2026-08-28 15:21:22', 2);
INSERT INTO `t_visit` VALUES (7, 'visitor_f21e1d2fb72a4171a0d5', 'session_2f1c467cc6a14b92a72e', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 15:21:44', '2026-08-28 15:21:44', 2);
INSERT INTO `t_visit` VALUES (8, 'visitor_f21e1d2fb72a4171a0d5', 'session_fc44bd8777324516880b', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 15:22:35', '2026-08-28 15:22:35', 2);
INSERT INTO `t_visit` VALUES (9, 'visitor_f21e1d2fb72a4171a0d5', 'session_9db95d6027e5473eb317', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 15:24:51', '2026-08-28 15:24:51', 2);
INSERT INTO `t_visit` VALUES (10, 'visitor_f21e1d2fb72a4171a0d5', 'session_31efa6f1dd7a4cc0b5f2', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 15:24:56', '2026-08-28 15:24:56', 2);
INSERT INTO `t_visit` VALUES (11, 'visitor_02dd724fbf36476cb014', 'session_956dad1212144c11b515', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-28 15:33:59', '2026-08-28 15:34:14', 5);
INSERT INTO `t_visit` VALUES (12, 'visitor_02dd724fbf36476cb014', 'session_e3088304b02a41b59223', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-28 15:34:18', '2026-08-28 15:34:25', 5);
INSERT INTO `t_visit` VALUES (13, 'visitor_f21e1d2fb72a4171a0d5', 'session_3a94919172d247e183e7', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:04:33', '2026-08-28 16:04:33', 2);
INSERT INTO `t_visit` VALUES (14, 'visitor_f21e1d2fb72a4171a0d5', 'session_ccac9504250641b9b92c', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:04:34', '2026-08-28 16:10:27', 8);
INSERT INTO `t_visit` VALUES (15, 'visitor_f21e1d2fb72a4171a0d5', 'session_afaeb4e193314189bfa2', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:10:30', '2026-08-28 16:10:30', 2);
INSERT INTO `t_visit` VALUES (16, 'visitor_f21e1d2fb72a4171a0d5', 'session_70ad8064ceff46788333', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:10:31', '2026-08-28 16:16:30', 3);
INSERT INTO `t_visit` VALUES (21, 'visitor_f21e1d2fb72a4171a0d5', 'session_9b998130cd42412abdfb', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:16:32', '2026-08-28 16:16:32', 2);
INSERT INTO `t_visit` VALUES (22, 'visitor_f21e1d2fb72a4171a0d5', 'session_34260d79c0d74d599a6f', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:16:33', '2026-08-28 16:17:40', 5);
INSERT INTO `t_visit` VALUES (23, 'visitor_f21e1d2fb72a4171a0d5', 'session_d017a9d755c74f56bd9c', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:17:42', '2026-08-28 16:17:42', 2);
INSERT INTO `t_visit` VALUES (24, 'visitor_f21e1d2fb72a4171a0d5', 'session_a14b430a879c48eda436', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:17:43', '2026-08-28 16:25:02', 4);
INSERT INTO `t_visit` VALUES (29, 'visitor_f21e1d2fb72a4171a0d5', 'session_51a8cc76b1964d10b6b1', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:25:05', '2026-08-28 16:25:05', 2);
INSERT INTO `t_visit` VALUES (30, 'visitor_f21e1d2fb72a4171a0d5', 'session_b546df5212ae4a63afa7', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-28 16:25:05', '2026-08-28 18:36:12', 13);
INSERT INTO `t_visit` VALUES (31, 'visitor_02dd724fbf36476cb014', 'session_e3677a3ef9d54f939064', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-28 16:29:48', '2026-08-28 17:42:18', 9);
INSERT INTO `t_visit` VALUES (32, 'visitor_02dd724fbf36476cb014', 'session_efe24bb0ed3344d89ae3', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-28 17:42:23', '2026-08-28 18:35:16', 5);
INSERT INTO `t_visit` VALUES (33, 'visitor_02dd724fbf36476cb014', 'session_fdcd818b318c4242a8fe', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-31 13:44:13', '2026-08-31 13:58:22', 6);
INSERT INTO `t_visit` VALUES (34, 'visitor_4a985d631b0b41f4afcc', 'session_23c3e8fbf3d5444dbb85', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-31 13:45:03', '2026-08-31 18:21:03', 1);
INSERT INTO `t_visit` VALUES (36, 'visitor_f21e1d2fb72a4171a0d5', 'session_008b467f31934ed2af6d', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-31 13:57:41', '2026-08-31 13:57:41', 2);
INSERT INTO `t_visit` VALUES (37, 'visitor_f21e1d2fb72a4171a0d5', 'session_c8e226b5a69d4324b0cc', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-08-31 13:57:42', '2026-08-31 18:34:38', 15);
INSERT INTO `t_visit` VALUES (38, 'visitor_5931b6010a994edcae0d', 'session_8318df82a41140489333', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-31 13:57:58', '2026-08-31 14:02:43', 5);
INSERT INTO `t_visit` VALUES (39, 'visitor_02dd724fbf36476cb014', 'session_d9fc409e48804e28903d', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-08-31 13:58:25', '2026-08-31 14:00:54', 3);
INSERT INTO `t_visit` VALUES (40, 'visitor_02dd724fbf36476cb014', 'session_887919d50ebb43e6ad7a', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-08-31 14:00:58', '2026-08-31 18:21:26', 8);
INSERT INTO `t_visit` VALUES (41, 'visitor_5931b6010a994edcae0d', 'session_acdeae5482d849898a32', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-08-31 14:02:49', '2026-08-31 14:31:31', 2);
INSERT INTO `t_visit` VALUES (42, 'visitor_02dd724fbf36476cb014', 'session_3d4f6dc008854758afbc', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-03 10:45:55', '2026-09-03 11:07:29', 7);
INSERT INTO `t_visit` VALUES (43, 'visitor_02dd724fbf36476cb014', 'session_b51dd345f61c4d8aa678', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-03 10:56:18', '2026-09-03 10:56:20', 2);
INSERT INTO `t_visit` VALUES (44, 'visitor_4a985d631b0b41f4afcc', 'session_b7013d9db23d4ec1a049', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/', '2026-09-03 11:03:03', '2026-09-03 11:03:34', 3);
INSERT INTO `t_visit` VALUES (45, 'visitor_02dd724fbf36476cb014', 'session_48eca95369b04004877a', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-03 11:11:38', '2026-09-03 11:18:43', 5);
INSERT INTO `t_visit` VALUES (47, 'visitor_02dd724fbf36476cb014', 'session_e4802e8707e24edda123', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-03 11:19:27', '2026-09-03 11:28:24', 3);
INSERT INTO `t_visit` VALUES (48, 'visitor_7f9ab40016e64e0ba8aa', 'session_b1d7dd1e302e4fd49a46', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-03 11:22:37', '2026-09-03 11:28:19', 5);
INSERT INTO `t_visit` VALUES (49, 'visitor_7f9ab40016e64e0ba8aa', 'session_f15f5ae1574545b092ad', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-03 11:27:22', '2026-09-03 11:28:20', 2);
INSERT INTO `t_visit` VALUES (50, 'visitor_02dd724fbf36476cb014', 'session_dc8036359f7749b6a520', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-03 11:28:46', '2026-09-03 13:06:24', 41);
INSERT INTO `t_visit` VALUES (51, 'visitor_4a985d631b0b41f4afcc', 'session_4005879f8d4643acad37', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/video', '2026-09-03 11:53:01', '2026-09-03 13:28:28', 8);
INSERT INTO `t_visit` VALUES (52, 'visitor_f21e1d2fb72a4171a0d5', 'session_cc59fc4dc3134376b567', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-03 12:06:59', '2026-09-03 12:06:59', 2);
INSERT INTO `t_visit` VALUES (53, 'visitor_f21e1d2fb72a4171a0d5', 'session_ca53d41c2ab8498fa971', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-03 12:07:00', '2026-09-03 18:04:36', 74);
INSERT INTO `t_visit` VALUES (54, 'visitor_f21e1d2fb72a4171a0d5', 'session_e2e497723b224d688ccb', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-03 13:29:21', '2026-09-03 13:29:21', 2);
INSERT INTO `t_visit` VALUES (55, 'visitor_4a985d631b0b41f4afcc', 'session_93a47ac0c773443c97c0', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Chrome', 'Windows', '/video', '2026-09-03 15:03:28', '2026-09-03 18:30:29', 4);
INSERT INTO `t_visit` VALUES (57, 'visitor_02dd724fbf36476cb014', 'session_0cc60e357cf2495bb963', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-04 14:50:57', '2026-09-04 18:29:16', 22);
INSERT INTO `t_visit` VALUES (58, 'visitor_f21e1d2fb72a4171a0d5', 'session_a7fff5645eba4952be62', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-04 15:40:57', '2026-09-04 15:40:57', 2);
INSERT INTO `t_visit` VALUES (59, 'visitor_f21e1d2fb72a4171a0d5', 'session_e0d82dc824ec46d0ba46', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-04 15:40:58', '2026-09-04 18:29:08', 29);
INSERT INTO `t_visit` VALUES (60, 'visitor_02dd724fbf36476cb014', 'session_dc3ce2b5d8f14afcae9a', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-07 13:00:51', '2026-09-07 13:00:56', 1);
INSERT INTO `t_visit` VALUES (62, 'visitor_f21e1d2fb72a4171a0d5', 'session_a7e0c7c75a23460fafb2', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-07 13:01:03', '2026-09-07 13:01:03', 2);
INSERT INTO `t_visit` VALUES (63, 'visitor_f21e1d2fb72a4171a0d5', 'session_7f7d5a5b5123475bb3f8', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-07 13:01:03', '2026-09-07 18:44:57', 43);
INSERT INTO `t_visit` VALUES (64, 'visitor_f21e1d2fb72a4171a0d5', 'session_22db938c72594688b48d', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-07 13:01:06', '2026-09-07 13:01:06', 2);
INSERT INTO `t_visit` VALUES (65, 'visitor_f21e1d2fb72a4171a0d5', 'session_53c0527a216948f38f2c', '127.0.0.1', '', 'direct', '直接访问', 'desktop', 'Edge', 'Windows', '/', '2026-09-07 13:13:33', '2026-09-07 13:13:33', 2);
INSERT INTO `t_visit` VALUES (66, 'visitor_02dd724fbf36476cb014', 'session_42415f66a5544972bb1a', '127.0.0.1', '', 'direct', '直接访问', 'desktop', '夸克', 'Windows', '/', '2026-09-08 15:59:15', '2026-09-08 18:11:35', 1);

-- ----------------------------
-- Table structure for t_visit_day
-- ----------------------------
DROP TABLE IF EXISTS `t_visit_day`;
CREATE TABLE `t_visit_day`  (
  `stat_date` date NOT NULL COMMENT '统计日期',
  `pv` int NULL DEFAULT 0 COMMENT '当日页面浏览量',
  `uv` int NULL DEFAULT 0 COMMENT '当日独立访客数',
  PRIMARY KEY (`stat_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '每日访问量汇总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_visit_day
-- ----------------------------
INSERT INTO `t_visit_day` VALUES ('2026-08-28', 148, 32);
INSERT INTO `t_visit_day` VALUES ('2026-08-31', 43, 9);
INSERT INTO `t_visit_day` VALUES ('2026-09-03', 158, 13);
INSERT INTO `t_visit_day` VALUES ('2026-09-04', 53, 3);
INSERT INTO `t_visit_day` VALUES ('2026-09-07', 50, 5);
INSERT INTO `t_visit_day` VALUES ('2026-09-08', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
