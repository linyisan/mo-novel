package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heng.valid.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 小说五星评分
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_rating")
@ApiModel(value="Rating对象", description="小说五星评分")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "小说评分（五星制）")
    @NotNull(message = "评分不能为空")
    @Range(min = 1, max = 5, message = "评分取值范围1-5")
    private Byte star;

    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "用户ID不能为空", groups = {AddGroup.class})
    private Long userId;

    @ApiModelProperty(value = "小说ID")
    @NotNull(message = "小说ID不能为空", groups = {AddGroup.class})
    private Long bookId;


}
