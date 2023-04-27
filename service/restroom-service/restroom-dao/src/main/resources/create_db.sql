use toilet_db;

create table toilet
(
id int auto_increment comment '主键' primary key,
clean tinyint DEFAULT 1 not NULL,
available tinyint DEFAULT 1 not NULL
);