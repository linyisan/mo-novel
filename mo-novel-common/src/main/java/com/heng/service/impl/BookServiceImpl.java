package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.heng.entity.CommentReply;
import com.heng.exception.BusinessException;
import com.heng.mapper.BookMapper;
import com.heng.mapper.CommentReplyMapper;
import com.heng.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.vo.BookSpVo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 小说信息表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public ResponseDTO addBook(Book book)
    {
        return null;
    }

    @SneakyThrows
    @Override
    public ResponseDTO searchByPage(Integer pageNo, Integer pageSize, BookSpVo queryParams)
    {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq(StringUtils.checkValNotNull(queryParams.getChannel()), "channel", queryParams.getChannel())
                .eq(StringUtils.checkValNotNull(queryParams.getCategoryId()), "category_id", queryParams.getCategoryId())
                .eq(StringUtils.checkValNotNull(queryParams.getBookStatus()), "status", queryParams.getBookStatus())
                .ge(StringUtils.checkValNotNull(queryParams.getWordCountMin()), "word_count", queryParams.getWordCountMin())
                .le(StringUtils.checkValNotNull(queryParams.getWordCountMax()), "word_count", queryParams.getWordCountMax())
                .ge(StringUtils.checkValNotNull(queryParams.getUpdateTimeMin()), "update_time", queryParams.getUpdateTimeMin())
                .orderByDesc(StringUtils.isNotBlank(queryParams.getSort()), StringUtils.camelToUnderline(queryParams.getSort()));
        bookQueryWrapper.and(StringUtils.checkValNotNull(queryParams.getKeyword()), qw -> qw
                .like("title", queryParams.getKeyword())
                .or().like("author_name", queryParams.getKeyword())
        );

        Page<Book> bookPage = new Page<>(pageNo, pageSize);
        bookMapper.selectPage(bookPage, bookQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", bookPage.getRecords());
        map.put("total", bookPage.getTotal());
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO selectBookById(Long bookId)
    {
        Book book = bookMapper.selectById(bookId);
        if (StringUtils.checkValNotNull(book)) throw new BusinessException("小说不存在");
        return ResponseDTO.succ(book);
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

    @Override
    public boolean addVisitCount(Long bookId, Long visitCount)
    {
        Book book = bookMapper.selectById(bookId);
        if (StringUtils.checkValNull(book)) throw new BusinessException("小说不存在");
        book.setVisitCount(book.getVisitCount() + 1);
        bookMapper.updateById(book);
        return true;
    }

    @Override
    public ResponseDTO addBookCommentReply(CommentReply commentReply)
    {
        if(this.existsBookComment(commentReply)) throw new BusinessException("已评价过该书籍！");
        commentReplyMapper.insert(commentReply);
        return ResponseDTO.succ("评论成功");
    }

    @Override
    public boolean existsBookComment(CommentReply commentReply)
    {
        // 评论: 资源类型=1 资源ID PID=0 + 唯一 from_user_id
        HashMap<String, Object> map = new HashMap<>();
        map.put("resource_type", 1);
        map.put("resource_id", commentReply.getResourceId());
        map.put("pid", 0);
        map.put("from_user_id", commentReply.getFromUserId());
        List<CommentReply> bookComments = commentReplyMapper.selectByMap(map);
        if (bookComments.isEmpty()) return false;
        else return true;
    }

}
