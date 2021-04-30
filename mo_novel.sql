CREATE TABLE `t_base` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY KEY (`id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_book` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小说名',
`author_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者名',
`channel` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '频道：0男频，1女频',
`category_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '小说分类ID',
`introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小说简介',
`cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小说封面',
`status` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '状态：0下架，1连载中，2已完结',
`word_count` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '小说总字数',
`visit_count` int(1) UNSIGNED NULL DEFAULT 0 COMMENT '总点击量',
PRIMARY KEY (`id`) ,
INDEX `fk_book_category` (`category_id` ASC) USING BTREE,
UNIQUE INDEX `unq_title_author` (`title` ASC, `author_name` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '小说信息表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_book_content` (
`id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`index_id` bigint UNSIGNED NOT NULL COMMENT '目录ID',
`content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '小说章节内容',
PRIMARY KEY (`id`) ,
INDEX `idx_indexid` (`index_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '小说章节内容表（mediumtext字段影响性能，从t_book_index表中分离）'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_booklist` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`user_id` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
`title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书单名',
`introduction` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书单简介',
`visit_count` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '总点击量',
PRIMARY KEY (`id`) ,
INDEX `idx_userid` (`user_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '用户书单'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_bookshelf` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`user_id` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
`book_id` int(10) UNSIGNED NOT NULL COMMENT '小说ID',
`reading_process` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '阅读进度：1正在追看，2养肥待看，3已经看过',
`reading_history_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '上次阅读章节ID',
PRIMARY KEY (`id`) ,
INDEX `fk_shelf_user` (`user_id` ASC) USING BTREE,
INDEX `fk_shelf_book` (`book_id` ASC) USING BTREE,
UNIQUE INDEX `unq_userid_bookid_progress` (`user_id` ASC, `book_id` ASC, `reading_process` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '用户书架'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_boolist_book` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`booklist_id` int(10) UNSIGNED NOT NULL COMMENT '书单ID',
`book_id` int(10) UNSIGNED NOT NULL COMMENT '小说ID',
`recommendation` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小说推荐语',
`book_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '（冗余）小说名',
`book_author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '（冗余）作者名',
`word_count` int(11) NULL DEFAULT NULL COMMENT '（冗余）小说字数',
PRIMARY KEY (`id`) ,
INDEX `fk_list_booklist` (`booklist_id` ASC) USING BTREE,
INDEX `fk_list_book` (`book_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '书单小说关联表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_category` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小说分类',
PRIMARY KEY (`id`) ,
UNIQUE INDEX `unq_name` (`name` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 8
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '小说分类表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_comment_reply` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`resource_type` tinyint(3) UNSIGNED NOT NULL COMMENT '目标资源类型：小说，书单',
`resource_id` int(10) UNSIGNED NOT NULL COMMENT '目标资源ID',
`pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复的父ID：0表示评论，其他均为回复',
`from_user_id` int(10) UNSIGNED NOT NULL COMMENT '本评论/回复的发出者用户ID',
`to_user_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '本评论/回复的目标用户ID，评论为null，当from=to即对评论的回复',
`content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论/回复内容',
`from_user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '（冗余）本评论/回复的发出者用户名',
`from_user_avater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '（冗余）本评论/回复的发出者用户头像',
`to_user_name` varchar(30) NULL COMMENT '（冗余）本评论/回复的目标用户用户名',
PRIMARY KEY (`id`) ,
INDEX `idx_type_resourceid_fromuserid` (`resource_type` ASC, `resource_id` ASC, `from_user_id` ASC) USING BTREE,
INDEX `fk_reply_fromuser` (`from_user_id` ASC) USING BTREE,
INDEX `fk_reply_touser` (`to_user_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '评价/回复表
TODO:使用触发器维护用户头像，用户名字段'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_like` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`resouce_type` int(10) UNSIGNED NOT NULL COMMENT '资源类型',
`resource_id` int(10) UNSIGNED NOT NULL COMMENT '资源ID',
`user_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
`like` tinyint(3) UNSIGNED NOT NULL COMMENT '喜欢或踩',
PRIMARY KEY (`id`) ,
UNIQUE INDEX `unq_type_rid_uid` (`resouce_type` ASC, `resource_id` ASC, `user_id` ASC) USING BTREE,
INDEX `fk_like_user` (`user_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '点赞踩表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_rating` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`start` tinyint(3) UNSIGNED NOT NULL DEFAULT 3 COMMENT '小说评分（五星制）',
`user_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
`book_id` int(10) UNSIGNED NOT NULL COMMENT '小说ID',
PRIMARY KEY (`id`) ,
INDEX `fk_rating_user` (`user_id` ASC) USING BTREE,
INDEX `fk_rating_book` (`book_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '小说五星评分'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_role` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
PRIMARY KEY (`id`) ,
UNIQUE INDEX `unq_name` (`name` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '角色表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_user` (
`id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
`password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
`avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像url',
`sex` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '性别：0男，1女',
`email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
`mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
`status` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '状态：0禁用，1可用',
`role_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '角色ID',
PRIMARY KEY (`id`) ,
UNIQUE INDEX `unq_username` (`username` ASC) USING BTREE,
INDEX `fk_user_role` (`role_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 7
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '用户表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;
CREATE TABLE `t_book_index` (
`id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`book_id` int UNSIGNED NOT NULL COMMENT '小说ID',
`word_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '章节字数',
`title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '章节名',
PRIMARY KEY (`id`) ,
INDEX `idx_bookid_utime` (`book_id` ASC, `update_time` ASC) USING BTREE,
UNIQUE INDEX `unq_bookid_title` (`book_id` ASC, `title` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '小说目录表'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Compact;

ALTER TABLE `t_book` ADD CONSTRAINT `fk_book_category` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `t_booklist` ADD CONSTRAINT `fk_booklist_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_bookshelf` ADD CONSTRAINT `fk_shelf_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_bookshelf` ADD CONSTRAINT `fk_shelf_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_boolist_book` ADD CONSTRAINT `fk_list_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_boolist_book` ADD CONSTRAINT `fk_list_booklist` FOREIGN KEY (`booklist_id`) REFERENCES `t_booklist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_comment_reply` ADD CONSTRAINT `fk_reply_fromuser` FOREIGN KEY (`from_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `t_comment_reply` ADD CONSTRAINT `fk_reply_touser` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `t_like` ADD CONSTRAINT `fk_like_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `t_rating` ADD CONSTRAINT `fk_rating_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_rating` ADD CONSTRAINT `fk_rating_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE `t_user` ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE `t_book_index` ADD CONSTRAINT `fk_index_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `t_book_content` ADD CONSTRAINT `fk_content_index` FOREIGN KEY (`index_id`) REFERENCES `t_book_index` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

