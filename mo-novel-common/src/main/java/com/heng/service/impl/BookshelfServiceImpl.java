package com.heng.service.impl;

import com.heng.entity.Bookshelf;
import com.heng.mapper.BookshelfMapper;
import com.heng.service.BookshelfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户书架 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
@Service
public class BookshelfServiceImpl extends ServiceImpl<BookshelfMapper, Bookshelf> implements BookshelfService {

}
