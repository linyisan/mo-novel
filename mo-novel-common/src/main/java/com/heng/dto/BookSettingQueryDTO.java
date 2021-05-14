package com.heng.dto;

import com.heng.vo.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author LJohn
 * @since 2021-05-15
 */
@Data
public class BookSettingQueryDTO extends BaseQuery {
    @ApiModelProperty(value = "类型，0：轮播图，1：顶部小说栏设置，2：本周强推，3：热门推荐，4：精品推荐")
    private Byte type;

    @ApiModelProperty(value = "小说ID")
    private Long bookId;

    @ApiModelProperty(value = "排序号")
    private Byte sort;
}
