package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.BookContent;
import com.heng.mapper.BookContentMapper;
import com.heng.service.BookContentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小说章节内容表（mediumtext字段影响性能，从t_book_index表中分离） 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class BookContentServiceImpl extends ServiceImpl<BookContentMapper, BookContent> implements BookContentService {

}
