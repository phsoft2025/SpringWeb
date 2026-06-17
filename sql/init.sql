-- 创建数据库
CREATE DATABASE IF NOT EXISTS crud_demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE crud_demo;

-- 用户表（JPA ddl-auto=update 会自动创建，此脚本供手动初始化使用）
CREATE TABLE IF NOT EXISTS sys_user (
    id          VARCHAR(36)  NOT NULL COMMENT '主键ID',
    username    VARCHAR(50)  NOT NULL COMMENT '用户名',
    email       VARCHAR(100) NULL     COMMENT '邮箱',
    phone       VARCHAR(20)  NULL     COMMENT '手机号',
    status      INT          NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    create_time DATETIME     NOT NULL COMMENT '创建时间',
    update_time DATETIME     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
