package com.heng.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LJohn
 * @since 2021-05-16
 */
@Data
public class NoticeQuery extends BaseQuery {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "消息体")
    private String content;
}
