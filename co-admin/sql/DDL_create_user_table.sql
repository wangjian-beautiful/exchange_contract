-- ----------------------------
-- 用户表
-- ----------------------------
drop table if exists user;
create table user (
  uid               bigint(20)      not null                   comment '用户ID',
  api_key           varchar(50)     not null                   comment 'open api 调用时使用的apiKey',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (uid)
) engine=innodb auto_increment=200 comment = '用户表';