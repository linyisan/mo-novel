package com.heng.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 请求响应的消息
 * @author LJohn
 * @since 2021-04-29
 */
@AllArgsConstructor
@Getter
public enum ResponseStatus {

    SUCCESS("success"),
    ERROR("未知异常"),

    // 评论相关错误
    HAS_COMMENT("已经评价过");


    private Integer code;
    private String msg;

    ResponseStatus(String msg)
    {
        this.msg = msg;
    }
}
