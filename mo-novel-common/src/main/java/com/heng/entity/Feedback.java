package com.heng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heng.vo.UserInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户反馈
 * </p>
 *
 * @author LJohn
 * @since 2021-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_feedback", resultMap = "BaseResultMap")
@ApiModel(value="Feedback对象", description="用户反馈")
public class Feedback implements Serializable {

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

    @ApiModelProperty(value = "消息体")
    @NotBlank(message = "反馈内容不能为空")
    private String content;

    @ApiModelProperty(value = "是否已经处理：0未处理，1已处理")
    private Byte done;

    @TableField(exist = false)
    private UserInfoVo userInfoVo;

}
