package com.heng.service;

import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.entity.Comment;
import com.heng.vo.BookSpVo;

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

    ResponseDTO searchByPage(Integer pageNo, Integer pageSize, BookSpVo queryParams);

    ResponseDTO selectBookById(Long bookId);

    ResponseDTO selectBook(Book book);

    ResponseDTO updateBook(Book book);

    boolean addVisitCount(Long bookId, Long visitCount);

    ResponseDTO addBookComment(Comment comment);

    ResponseDTO updateBookComment(Long commentId, String content);

    boolean existsBookComment(Comment comment);
}
