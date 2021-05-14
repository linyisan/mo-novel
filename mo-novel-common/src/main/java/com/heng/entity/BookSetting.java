package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heng.valid.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 首页小说设置表
 * </p>
 *
 * @author LJohn
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_book_setting")
@ApiModel(value="BookSetting对象", description="首页小说设置表")
public class BookSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "小说ID")
    @NotNull(message = "小说ID不能为空")
    private Long bookId;

    @ApiModelProperty(value = "类型，0：轮播图，1：顶部小说栏设置，2：本周强推，3：热门推荐，4：精品推荐")
    @NotNull(message = "小说ID不能为空")
    private Byte type;

    @ApiModelProperty(value = "排序号")
    private Byte sort;

    @TableField(exist = false)
    private Book book;
}
