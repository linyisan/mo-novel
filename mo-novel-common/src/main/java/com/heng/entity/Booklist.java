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
 * 用户书单
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_booklist")
@ApiModel(value="Booklist对象", description="用户书单")
public class Booklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "书单名")
    private String title;

    @ApiModelProperty(value = "书单简介")
    private String introduction;

    @ApiModelProperty(value = "总点击量")
    private Long visitCount;


}
