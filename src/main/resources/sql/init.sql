CREATE DATABASE IF NOT EXISTS `work_life_bell` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `work_life_bell`;

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance`
(
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT NULL,
    work_date DATE NULL,
    on_work_date_time DATETIME DEFAULT NULL,
    off_work_date_time DATETIME DEFAULT NULL,
    CONSTRAINT attendance_pk PRIMARY KEY (id),
    UNIQUE KEY user_id_work_date (user_id, work_date)
);
