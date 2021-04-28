package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.Booklist;
import com.heng.mapper.BooklistMapper;
import com.heng.service.BooklistService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户书单 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class BooklistServiceImpl extends ServiceImpl<BooklistMapper, Booklist> implements BooklistService {

}
