CREATE DATABASE IF NOT EXISTS `work_life_bell` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `work_life_bell`;

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance`
(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT(20) NULL,
    work_date DATE NULL,
    on_work_date_time DATETIME DEFAULT NULL,
    off_work_date_time DATETIME DEFAULT NULL,
    UNIQUE KEY user_id_work_date_uk (user_id, work_date)
);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    public_id VARCHAR(255) NOT NULL,
    login_type VARCHAR(255) NOT NULL,
    last_login_date_time DATETIME NOT NULL,
    deleted bit(1) NOT NULL,
    deleted_date_time DATETIME DEFAULT NULL,
    UNIQUE KEY public_id_uk (public_id)
);

DROP TABLE IF EXISTS `email_user`;

CREATE TABLE `email_user`
(
    user_id BIGINT(20) PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    password_fail_count INT(11) DEFAULT 0,
    password_change_date_time DATETIME NOT NULL,
    UNIQUE KEY email_uk (email)
);