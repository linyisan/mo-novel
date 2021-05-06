package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 小说信息表
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_book")
@ApiModel(value="Book对象", description="小说信息表")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "小说名")
    @NotBlank(message = "小说名不能为空")
    private String title;

    @ApiModelProperty(value = "作者名")
    @NotBlank(message = "作者名不能为空")
    private String authorName;

    @ApiModelProperty(value = "频道：0男频，1女频")
    private Byte channel;

    @ApiModelProperty(value = "小说分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "小说简介")
    @NotBlank(message = "小说简介不能为空")
    private String introduction;

    @ApiModelProperty(value = "小说封面")
    private String cover;

    @ApiModelProperty(value = "状态：0下架，1连载中，2已完结")
    private Byte status;

    @ApiModelProperty(value = "小说总字数")
    private Long wordCount;

    @ApiModelProperty(value = "总点击量")
    private Long visitCount;

    @TableField(exist = false)
    private Category category;

}
