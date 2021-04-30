package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评价/回复表	TODO:使用触发器维护用户头像，用户名字段
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_comment_reply")
@ApiModel(value="CommentReply对象", description="评价/回复表	TODO:使用触发器维护用户头像，用户名字段")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "目标资源类型：小说，书单")
    @NotNull(message = "评价的资源类型不能为空")
    private Byte resourceType;

    @NotNull(message = "评价的资源ID不能为空")
    @ApiModelProperty(value = "目标资源ID")
    private Long resourceId;

    @ApiModelProperty(value = "回复的父ID：0表示评论，其他均为回复")
    @NotNull(message = "无法区分评论还是回复")
    private Long pid;

    @ApiModelProperty(value = "本评论/回复的发出者用户ID")
    @NotNull(message = "评论回复者ID不能为空")
    private Long fromUserId;

    @ApiModelProperty(value = "本评论/回复的目标用户ID，评论为null，当from=to即对评论的回复")
    private Long toUserId;

    @ApiModelProperty(value = "评论/回复内容")
    private String content;

    @ApiModelProperty(value = "（冗余）本评论/回复的发出者用户名")
    private String fromUserName;

    @ApiModelProperty(value = "（冗余）本评论/回复的发出者用户头像")
    private String fromUserAvater;

}
