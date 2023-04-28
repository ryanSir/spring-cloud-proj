use toilet_db;

create table toilet
(
id int auto_increment comment '主键' primary key,
clean tinyint DEFAULT 1 not NULL,
available tinyint DEFAULT 1 not NULL
);

CREATE TABLE `employee_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL,
  `activity_type` int(11) NOT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `start_time` timestamp(6) NULL DEFAULT NULL,
  `end_time` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;