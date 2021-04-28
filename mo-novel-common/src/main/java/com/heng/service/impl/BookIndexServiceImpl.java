package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.BookIndex;
import com.heng.mapper.BookIndexMapper;
import com.heng.service.BookIndexService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小说目录表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class BookIndexServiceImpl extends ServiceImpl<BookIndexMapper, BookIndex> implements BookIndexService {

}
