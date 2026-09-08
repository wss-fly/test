-- =====================================================================
-- 飞推引流网站 - 后台管理系统数据库初始化脚本
-- 复用 feitui 数据库(与业务后端共用)，新增后台管理相关表
-- =====================================================================
CREATE DATABASE IF NOT EXISTS feitui DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE feitui;

-- ---------------------------------------------------------------------
-- 1. 后台管理员账号表
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
    `id`             BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`       VARCHAR(50)  NOT NULL COMMENT '登录用户名',
    `password`       VARCHAR(200) NOT NULL COMMENT '密码(MD5加密)',
    `nickname`       VARCHAR(50)  DEFAULT NULL COMMENT '昵称',
    `role`           VARCHAR(20)  DEFAULT 'ADMIN' COMMENT '角色',
    `status`         TINYINT      DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    `last_login_time` DATETIME    DEFAULT NULL COMMENT '最后登录时间',
    `create_time`    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后台管理员账号表';

-- 默认超级管理员：admin / admin123 （密码为 MD5(admin123) = 0192023a7bbd73250516f069df18b500）
-- 超级管理员全局唯一，且不可删除、不可修改
INSERT INTO `t_admin_user` (`username`, `password`, `nickname`, `role`, `status`)
SELECT 'admin', '0192023a7bbd73250516f069df18b500', '超级管理员', 'SUPER', 1
WHERE NOT EXISTS (SELECT 1 FROM `t_admin_user` WHERE `username` = 'admin');

-- ---------------------------------------------------------------------
-- 2. 访问会话表（每个浏览器会话一行，心跳更新在线状态）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `t_visit`;
CREATE TABLE `t_visit` (
    `id`            BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `visitor_id`    VARCHAR(64)  NOT NULL COMMENT '访客唯一ID(浏览器localStorage)',
    `session_id`    VARCHAR(64)  NOT NULL COMMENT '会话ID',
    `ip`            VARCHAR(64)  DEFAULT NULL COMMENT '访客IP',
    `referrer`      VARCHAR(500) DEFAULT NULL COMMENT '来源页面URL',
    `source`        VARCHAR(64)  DEFAULT NULL COMMENT 'utm_source或来源标记',
    `channel`       VARCHAR(64)  DEFAULT NULL COMMENT '渠道分类(Facebook/搜索引擎/直接访问等)',
    `device`        VARCHAR(32)  DEFAULT NULL COMMENT '设备类型(mobile/tablet/desktop)',
    `browser`       VARCHAR(32)  DEFAULT NULL COMMENT '浏览器',
    `os`            VARCHAR(32)  DEFAULT NULL COMMENT '操作系统',
    `landing_page`  VARCHAR(255) DEFAULT NULL COMMENT '落地页面',
    `first_time`    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '会话开始时间',
    `last_active`   DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最近活跃时间(用于在线判定)',
    `pv`            INT DEFAULT 1 COMMENT '本会话浏览量',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_session` (`session_id`),
    KEY `idx_channel` (`channel`),
    KEY `idx_last_active` (`last_active`),
    KEY `idx_first_time` (`first_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问会话表';

-- ---------------------------------------------------------------------
-- 3. 每日访问量汇总表（用于趋势图与今日统计）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `t_visit_day`;
CREATE TABLE `t_visit_day` (
    `stat_date` DATE NOT NULL COMMENT '统计日期',
    `pv`        INT DEFAULT 0 COMMENT '当日页面浏览量',
    `uv`        INT DEFAULT 0 COMMENT '当日独立访客数',
    PRIMARY KEY (`stat_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每日访问量汇总表';