package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.heng.mapper.BookMapper;
import com.heng.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 小说信息 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseDTO addBook(Book book)
    {
        return null;
    }

    @Override
    public ResponseDTO pageBook(Integer pageNo, Integer pageSize)
    {
        Page<Book> bookPage = new Page<>(pageNo, pageSize);
        bookMapper.selectPage(bookPage, null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", bookPage.getRecords());
        map.put("total", bookPage.getTotal());
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO selectBookById(Integer bookId)
    {
        return null;
    }

    @Override
    public ResponseDTO selectBook(Book book)
    {
        return null;
    }

    @Override
    public ResponseDTO updateBook(Book book)
    {
        return null;
    }
}
