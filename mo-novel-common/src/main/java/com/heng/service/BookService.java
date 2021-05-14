package com.heng.service;

import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.entity.Comment;
import com.heng.vo.BookQueryVo;

import java.util.List;

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

    /**
     * 增加点击次数
     * @param bookId 书籍ID
     * @param visitCount*/
    boolean addVisitCount(Long bookId, Long visitCount);

    /**
     * 修改书籍评论数
     * @param bookId 书籍ID
     * @param increment 书评增量
     **/
    boolean changeCommentCount(Long bookId, Long increment);

    ResponseDTO addBookComment(Comment comment);

    ResponseDTO editBookComment(Comment comment);

    ResponseDTO deleteBookComment(Long commentId);

    boolean existsBookComment(Comment comment);

    /**
     * 查询小说排行信息
     * @param type 排行类型，0点击排行，1新书排行，2书评数量排行,默认更新排行
     * @param limit 查询条数
     * @return 小说集合
     * */
    List<Book> listRank(Byte type, Integer limit);
}
