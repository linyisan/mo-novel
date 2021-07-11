package com.heng.aop;

import com.heng.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LJohn
 * @since 2021-05-05
 */
@ResponseBody
@RestControllerAdvice
@Slf4j
public class ShiroExceptionHandler {
    // shiro相关 start
    @ExceptionHandler(UnknownAccountException.class)
    public ResponseDTO UnknownAccountExceptionHandler(UnknownAccountException e)
    {
        return ResponseDTO.fail("用户名不存在");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseDTO IncorrectCredentialsExceptionHandler(IncorrectCredentialsException e)
    {
        return ResponseDTO.fail("密码错误");
    }

    @ExceptionHandler(LockedAccountException.class)
    public ResponseDTO LockedAccountExceptionHandler(LockedAccountException e)
    {
        return ResponseDTO.fail("登录验证过多, 账户已锁定");
    }

    @ExceptionHandler(ExcessiveAttemptsException.class)
    public ResponseDTO ExcessiveAttemptsExceptionHandler(ExcessiveAttemptsException e)
    {
        return ResponseDTO.fail("shiro登录认证次数过多, 请xx分钟后再试");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseDTO AuthenticationExceptionHandler(AuthenticationException e)
    {
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        log.error("shiro认证失败");
        return ResponseDTO.fail(throwable.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseDTO AuthorizationExceptionHandler(AuthorizationException e)
    {
        return ResponseDTO.fail("没有相关授权");
    }

    // shiro相关 end
}
