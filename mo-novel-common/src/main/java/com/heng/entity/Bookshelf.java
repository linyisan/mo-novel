package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户书架
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_bookshelf", resultMap = "BaseResultMap")
@ApiModel(value="Bookshelf对象", description="用户书架")
public class Bookshelf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(value = "小说ID")
    @NotNull(message = "小说ID不能为空")
    private Long bookId;

    @ApiModelProperty(value = "阅读进度：1正在追看，2养肥待看，3已经看过")
    @Range(min = 1, max = 3, message = "阅读进度错误")
    private Byte readingProcess;

    @ApiModelProperty(value = "上次阅读章节ID")
    private Long readingHistoryId;

    @TableField(exist = false)
    private Book book;
}
