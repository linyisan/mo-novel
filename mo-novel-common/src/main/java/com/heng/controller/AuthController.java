package com.heng.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.heng.common.ResponseDTO;
import com.heng.entity.User;
import com.heng.service.UserService;
import com.heng.vo.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 用户认证
 *
 * @author LJohn
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseDTO register(@Validated @RequestBody User user)
    {
        return userService.register(user);
    }

    @PostMapping("/logout")
    public ResponseDTO logout(@RequestHeader("X-Token") String token)
    {
        SecurityUtils.getSubject().logout();
        return ResponseDTO.succ("成功退出登录");
    }


    /**
     * jwt+shiro
     * 一次认证 二次认证在Realm
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginVo loginVo)
    {
        return userService.login(loginVo);
    }

    @GetMapping("/info")
    public ResponseDTO info(String token)
    {
//        if()
        return userService.info(token);
    }

}
