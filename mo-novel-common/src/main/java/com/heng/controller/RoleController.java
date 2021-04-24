package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Role;
import com.heng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseDTO add(Role role)
    {
        if(roleService.save(role))
            return ResponseDTO.succ("成功添加新角色");
        return ResponseDTO.fail("添加角色失败");
    }
}

