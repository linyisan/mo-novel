package com.heng.service;

import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 小说信息表 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
public interface BookService extends IService<Book> {
    ResponseDTO addBook(Book book);

    ResponseDTO pageBook(Integer pageNo, Integer pageSize);

    ResponseDTO selectBookById(Integer bookId);

    ResponseDTO selectBook(Book book);

    ResponseDTO updateBook(Book book);
}
