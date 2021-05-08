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

    @GetMapping("/logout")
    public ResponseDTO logout(@RequestHeader("X-Token") String token)
    {
        SecurityUtils.getSubject().logout();
        return ResponseDTO.succ("成功退出登录");
    }

    @PostMapping("/login1")
    public ResponseDTO login1(LoginVo loginVo)
    {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //没有认证过
        //封装用户的登录数据,获得令牌
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword());
        subject.login(token);
        String str_token = "";//JwtUtils.sign(loginVo.getUsername(), "-1");
        User user = (User) subject.getPrincipal();
        if (StringUtils.checkValNotNull(user) && user.getStatus() == 0) throw new LockedAccountException();
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return ResponseDTO.succ("登录成功", map);
        //登录 及 异常处理
/*        try
        {
            //用户登录
            subject.login(token);
            return ResponseDTO.succ("登录成功", token);
        } catch (UnknownAccountException uae)
        {
            //如果用户名不存在
            return ResponseDTO.fail("用户名不存在");
        } catch (IncorrectCredentialsException ice)
        {
            //如果密码错误
            return ResponseDTO.fail("密码错误");

        }*/
    }

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
