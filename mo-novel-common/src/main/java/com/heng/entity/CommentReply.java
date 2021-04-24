package com.heng.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评价/回复表
TODO:使用触发器维护用户头像，用户名字段
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_comment_reply")
public class CommentReply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 目标资源类型：小说，书单
     */
    private Integer resourceType;

    /**
     * 目标资源ID
     */
    private Integer resourceId;

    /**
     * 回复的父ID：0表示评论，其他均为回复
     */
    private Integer pid;

    /**
     * 本评论/回复的发出者用户ID
     */
    private Integer fromUserId;

    /**
     * 本评论/回复的目标用户ID，评论为null，当from=to即对评论的回复
     */
    private Integer toUserId;

    /**
     * 评论/回复内容
     */
    private String content;

    /**
     * （冗余）本评论/回复的发出者用户名
     */
    private String fromUserName;

    /**
     * （冗余）本评论/回复的发出者用户头像
     */
    private String fromUserAvater;


}
