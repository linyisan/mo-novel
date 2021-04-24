package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Category;
import com.heng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if(categoryService.save(category))
            return ResponseDTO.succ("成功添加分类");
        return ResponseDTO.fail("添加失败");
    }
}

