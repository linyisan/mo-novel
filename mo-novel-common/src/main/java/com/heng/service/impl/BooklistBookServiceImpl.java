package com.heng.service.impl;

import com.heng.entity.BooklistBook;
import com.heng.mapper.BooklistBookMapper;
import com.heng.service.BooklistBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 书单-小说关联 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
@Service
public class BooklistBookServiceImpl extends ServiceImpl<BooklistBookMapper, BooklistBook> implements BooklistBookService {

}
