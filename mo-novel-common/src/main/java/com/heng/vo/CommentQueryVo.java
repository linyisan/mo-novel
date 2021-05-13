package com.heng.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LJohn
 * @since 2021-05-11
 */
@Data
public class CommentQueryVo extends BaseQuery {
    @ApiModelProperty(value = "目标资源类型：小说，书单")
    private Byte resourceType;

    @ApiModelProperty(value = "目标资源ID")
    private Long resourceId;

    @ApiModelProperty(value = "本评论发出者用户ID")
    private Long userId;

    @ApiModelProperty(value = "评论内容")
    private String content;
}
