CREATE TABLE `t_base`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_book`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `title` varchar(30) NOT NULL COMMENT '小说名',
  `author_name` varchar(10) NOT NULL COMMENT '作者名',
  `channel` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '频道：0男频，1女频',
  `category_id` tinyint UNSIGNED NULL COMMENT '小说分类ID',
  `introduction` varchar(1000) NULL COMMENT '小说简介',
  `cover` varchar(255) NULL COMMENT '小说封面',
  `status` tinyint UNSIGNED NULL DEFAULT 1 COMMENT '状态：0下架，1连载中，2已完结',
  `word_count` int UNSIGNED NULL DEFAULT 0 COMMENT '小说总字数',
  `visit_count` binary NULL DEFAULT 0 COMMENT '总点击量',
  PRIMARY KEY (`id`)
) COMMENT = '小说信息表';

CREATE TABLE `t_book_content`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `book_id` int UNSIGNED NOT NULL COMMENT '小说ID',
  `word_count` int UNSIGNED NULL COMMENT '本章字数',
  `index_num` int UNSIGNED NOT NULL COMMENT '章节序号',
  `index_title` varchar(30) NOT NULL COMMENT '章节标题',
  `content` mediumtext NULL COMMENT '本章节内容',
  PRIMARY KEY (`id`),
  INDEX `idx_bookid_indexnum`(`book_id`, `index_num`) USING BTREE
) COMMENT = '小说内容表';

CREATE TABLE `t_booklist`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` int UNSIGNED NOT NULL COMMENT '用户ID',
  `title` varchar(50) NOT NULL COMMENT '书单名',
  `introduction` varchar(500) NULL COMMENT '书单简介',
  `visit_count` bigint UNSIGNED NULL DEFAULT 0 COMMENT '总点击量',
  PRIMARY KEY (`id`),
  INDEX `idx_userid`(`user_id`) USING BTREE
) COMMENT = '用户书单';

CREATE TABLE `t_bookshelf`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` int UNSIGNED NOT NULL COMMENT '用户ID',
  `book_id` int UNSIGNED NOT NULL COMMENT '小说ID',
  `reading_process` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '阅读进度：1正在追看，2养肥待看，3已经看过',
  `reading_history_id` int UNSIGNED NULL COMMENT '上次阅读章节ID',
  PRIMARY KEY (`id`)
) COMMENT = '用户书架';

CREATE TABLE `t_boolist_book`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `booklist_id` int UNSIGNED NOT NULL COMMENT '书单ID',
  `book_id` int UNSIGNED NOT NULL COMMENT '小说ID',
  `recommendation` varchar(500) NULL COMMENT '小说推荐语',
  `book_name` varchar(30) NULL COMMENT '（冗余）小说名',
  `book_author` varchar(30) NULL COMMENT '（冗余）作者名',
  `word_count` int NULL COMMENT '（冗余）小说字数',
  PRIMARY KEY (`id`)
) COMMENT = '书单小说关联表';

CREATE TABLE `t_category`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `name` varchar(255) NOT NULL COMMENT '小说分类',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unq_name`(`name`) USING BTREE
) COMMENT = '小说分类表';

CREATE TABLE `t_comment_reply`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `resource_type` tinyint UNSIGNED NOT NULL COMMENT '目标资源类型：小说，书单',
  `resource_id` int UNSIGNED NOT NULL COMMENT '目标资源ID',
  `pid` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复的父ID：0表示评论，其他均为回复',
  `from_user_id` int UNSIGNED NOT NULL COMMENT '本评论/回复的发出者用户ID',
  `to_user_id` int UNSIGNED NULL COMMENT '本评论/回复的目标用户ID，评论为null，当from=to即对评论的回复',
  `content` varchar(500) NULL COMMENT '评论/回复内容',
  `from_user_name` varchar(30) NULL COMMENT '（冗余）本评论/回复的发出者用户名',
  `from_user_avater` varchar(255) NULL COMMENT '（冗余）本评论/回复的发出者用户头像',
  PRIMARY KEY (`id`),
  INDEX `idx_type_resourceid_fromuserid`(`resource_type`, `resource_id`, `from_user_id`) USING BTREE
) COMMENT = '评价/回复表\r\nTODO:使用触发器维护用户头像，用户名字段';

CREATE TABLE `t_like`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `resouce_type` int UNSIGNED NOT NULL COMMENT '资源类型',
  `resource_id` int UNSIGNED NOT NULL COMMENT '资源ID',
  `user_id` int UNSIGNED NULL COMMENT '用户ID',
  `like` tinyint UNSIGNED NOT NULL COMMENT '喜欢或踩',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unq_type_rid_uid`(`resouce_type`, `resource_id`, `user_id`) USING BTREE
) COMMENT = '点赞踩表';

CREATE TABLE `t_rating`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `start` tinyint UNSIGNED NOT NULL DEFAULT 3 COMMENT '小说评分（五星制）',
  `user_id` int UNSIGNED NULL COMMENT '用户ID',
  `book_id` int UNSIGNED NOT NULL COMMENT '小说ID',
  PRIMARY KEY (`id`)
) COMMENT = '小说五星评分';

CREATE TABLE `t_role`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `name` varchar(30) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unq_name`(`name`) USING BTREE
) COMMENT = '角色表';

CREATE TABLE `t_user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `avatar` varchar(255) NULL COMMENT '头像url',
  `sex` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '性别：0男，1女',
  `email` varchar(100) NULL COMMENT '邮件',
  `mobile` varchar(11) NULL COMMENT '手机号',
  `status` tinyint UNSIGNED NULL DEFAULT 1 COMMENT '状态：0禁用，1可用',
  `role_id` int UNSIGNED NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unq_username`(`username`) USING BTREE
) COMMENT = '用户表';

ALTER TABLE `t_book` ADD CONSTRAINT `fk_book_category` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE `t_book_content` ADD CONSTRAINT `fk_content_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_booklist` ADD CONSTRAINT `fk_booklist_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_bookshelf` ADD CONSTRAINT `fk_shelf_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_bookshelf` ADD CONSTRAINT `fk_shelf_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_boolist_book` ADD CONSTRAINT `fk_list_booklist` FOREIGN KEY (`booklist_id`) REFERENCES `t_booklist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_boolist_book` ADD CONSTRAINT `fk_list_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_comment_reply` ADD CONSTRAINT `fk_reply_fromuser` FOREIGN KEY (`from_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `t_comment_reply` ADD CONSTRAINT `fk_reply_touser` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `t_like` ADD CONSTRAINT `fk_like_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `t_rating` ADD CONSTRAINT `fk_rating_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE `t_rating` ADD CONSTRAINT `fk_rating_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_user` ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

