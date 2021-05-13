package com.heng.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LJohn
 * @since 2021-05-11
 */
@Data
public class RatingQueryVo extends BaseQuery {
    @ApiModelProperty(value = "小说评分（五星制）")
    private Byte star;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "小说ID")
    private Long bookId;
}
