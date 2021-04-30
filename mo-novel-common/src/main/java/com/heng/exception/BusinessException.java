package com.heng.exception;

import lombok.Data;

/**
 * 自定义业务异常，用于处理用户请求时，业务错误时抛出
 * @author LJohn
 * @since 2021-04-29
 */
@Data
public class BusinessException extends RuntimeException{
    private String message;
    public BusinessException(String message)
    {
        super(message);
        this.message = message;
    }
}
