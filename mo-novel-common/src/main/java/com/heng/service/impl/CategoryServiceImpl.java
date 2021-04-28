package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.Category;
import com.heng.mapper.CategoryMapper;
import com.heng.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小说分类表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
