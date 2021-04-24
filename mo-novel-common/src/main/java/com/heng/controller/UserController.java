package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.User;
import com.heng.service.UserService;
import com.heng.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


/*    @PostMapping("/login1")
    public ResponseDTO login1(@RequestParam("username") String username,
                              @RequestParam("password") String password)
    {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //没有认证过
        //封装用户的登录数据,获得令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //登录 及 异常处理
        try
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

        }
    }*/

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

    @PostMapping("register")
    public ResponseDTO register(@Validated User user)
    {
        return userService.register(user);
    }

    @PostMapping("/logout")
    public ResponseDTO logout(@RequestHeader("X-Token") String token)
    {
//        SecurityUtils.getSubject().logout();
        return ResponseDTO.succ("成功退出登录", null);
    }

    @GetMapping("/list")
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer pageNo,
                            @RequestParam(defaultValue = "5") Integer pageSize)
    {
        return userService.page(pageNo, pageSize);
    }
}

