package com.heng.aop;

import com.heng.common.ResponseDTO;
import com.heng.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * @author Jodas
 * @create 2021--04--24    5:12
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    public ResponseDTO BindExceptionHandler(BindException e) {
        e.printStackTrace();
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return ResponseDTO.fail(message);
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseDTO ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        e.printStackTrace();
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return ResponseDTO.fail(message);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return ResponseDTO.fail(message);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseDTO SQLExceptionHandler(SQLException e)
    {
        e.printStackTrace();
        return ResponseDTO.fail("SQL操作失败");
    }

    // 业务异常
    @ExceptionHandler(BusinessException.class)
    public ResponseDTO BusinessExceptionHandler(BusinessException e)
    {
        return ResponseDTO.fail(e.getMessage());
    }

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
        return ResponseDTO.fail("shiro认证失败");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseDTO AuthorizationExceptionHandler(AuthorizationException e)
    {
        return ResponseDTO.fail("没有相关授权");
    }

    // shiro相关 end

//    @ExceptionHandler(RuntimeException.class)
    public ResponseDTO RuntimeExceptionHandler(RuntimeException e)
    {
        return ResponseDTO.fail("全局:" + e.getMessage());
    }
}
