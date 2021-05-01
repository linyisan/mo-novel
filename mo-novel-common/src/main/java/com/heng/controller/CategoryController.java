package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Category;
import com.heng.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 * 小说分类表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseDTO add(Category category)
    {
        if (categoryService.save(category))
            return ResponseDTO.succ("成功添加分类");
        return ResponseDTO.fail("添加失败");
    }

    @GetMapping("list")
//    @RequiresAuthentication
//    @RequiresRoles(value = {"admin"})
    public ResponseDTO list()
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", categoryService.list());
        return ResponseDTO.succ(map);
    }
}

