package com.heng.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.heng.common.ResponseDTO;
import com.heng.entity.User;
import com.heng.service.UserService;
import com.heng.util.JwtUtils;
import com.heng.vo.LoginVo;
import com.heng.vo.UserQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseDTO searchUser(UserQueryVo userQueryVo)
    {
        return userService.searchUser(userQueryVo);
    }

    @PostMapping("add")
    public ResponseDTO addUser(@Validated @RequestBody User user)
    {
        return userService.register(user);
    }

    @PostMapping("edit")
    public ResponseDTO editUser(@Validated @RequestBody User user)
    {
        userService.updateById(user);
        return ResponseDTO.succ("成功修改用户");
    }

    @GetMapping("delete/{userId}")
    public ResponseDTO deleteUser(@PathVariable Long userId)
    {

        if (userService.removeById(userId))
        {
            return ResponseDTO.succ("成功删除");
        }else
            return ResponseDTO.fail("删除失败");
    }
}

