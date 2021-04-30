package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 小说章节内容表（mediumtext字段影响性能，从t_book_index表中分离）
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_book_content")
@ApiModel(value="BookContent对象", description="小说章节内容表（mediumtext字段影响性能，从t_book_index表中分离）")
public class BookContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "目录ID")
    private Long indexId;

    @ApiModelProperty(value = "小说章节内容")
    private String content;


}
