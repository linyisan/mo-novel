package com.heng.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LJohn
 * @since 2021-05-16
 */
@Data
public class FeedbackQuery extends BaseQuery {
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "消息体")
    private String content;

    @ApiModelProperty(value = "是否已经处理：0未处理，1已处理")
    private Byte done;
}
