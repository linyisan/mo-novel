package com.heng.service;

import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.entity.Comment;
import com.heng.vo.BookQueryVo;

/**
 * <p>
 * 小说信息表 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
public interface BookService extends IService<Book> {
    ResponseDTO searchBook(BookQueryVo queryParams);

    ResponseDTO selectBookById(Long bookId);

    boolean addVisitCount(Long bookId, Long visitCount);

    ResponseDTO addBookComment(Comment comment);

    ResponseDTO editBookComment(Long commentId, String content);

    boolean existsBookComment(Comment comment);
}
