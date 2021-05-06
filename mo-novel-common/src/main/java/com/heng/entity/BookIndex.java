package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 小说目录表
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_book_index")
@ApiModel(value="BookIndex对象", description="小说目录表")
public class BookIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "小说ID")
    @NotNull( message = "小说ID不能为空")
    private Long bookId;

    @ApiModelProperty(value = "章节字数")
    private Long wordCount;

    @ApiModelProperty(value = "章节名")
    @NotBlank(message = "章节名不能为空")
    private String title;

    @ApiModelProperty(value = "状态:0草稿，1发布")
    @NotNull(message = "小说状态不能为空")
    private Byte status;

    @ApiModelProperty(value = "章节内容")
    @TableField(exist = false)
    @NotBlank(message = "小说内容不能为空")
    private String content;

}
