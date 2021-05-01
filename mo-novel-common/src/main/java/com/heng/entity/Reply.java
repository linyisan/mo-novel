package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 回复表
 * </p>
 *
 * @author LJohn
 * @since 2021-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_reply")
@ApiModel(value="Reply对象", description="回复表")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "回复ID")
    private Long commentId;

    @ApiModelProperty(value = "本回复发出者用户ID")
    private Long userId;

    @ApiModelProperty(value = "回复内容")
    private String content;


}
