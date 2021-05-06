package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.common.ResponseDTO;
import com.heng.common.ResponseStatus;
import com.heng.entity.Book;
import com.heng.entity.Comment;
import com.heng.exception.BusinessException;
import com.heng.mapper.BookMapper;
import com.heng.mapper.CommentMapper;
import com.heng.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.vo.BookQueryVo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CommentMapper commentMapper;

    @SneakyThrows
    @Override
    public ResponseDTO searchBook(BookQueryVo queryParams)
    {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq(StringUtils.checkValNotNull(queryParams.getChannel()), "channel", queryParams.getChannel())
                .eq(StringUtils.checkValNotNull(queryParams.getCategoryId()), "category_id", queryParams.getCategoryId())
                .eq(StringUtils.checkValNotNull(queryParams.getBookStatus()), "status", queryParams.getBookStatus())
                .ge(StringUtils.checkValNotNull(queryParams.getWordCountMin()), "word_count", queryParams.getWordCountMin())
                .le(StringUtils.checkValNotNull(queryParams.getWordCountMax()), "word_count", queryParams.getWordCountMax())
                .ge(StringUtils.checkValNotNull(queryParams.getUpdateTimeMin()), "update_time", queryParams.getUpdateTimeMin())
                .orderByDesc(StringUtils.isNotBlank(queryParams.getSort()), StringUtils.camelToUnderline(queryParams.getSort()))
                .like(StringUtils.isNotBlank(queryParams.getAuthorName()), "author_name", queryParams.getAuthorName())
                .like(StringUtils.isNotBlank(queryParams.getTitle()), "title", queryParams.getTitle());
        bookQueryWrapper.and(StringUtils.checkValNotNull(queryParams.getKeyword()), qw -> qw
                .like("title", queryParams.getKeyword())
                .or().like("author_name", queryParams.getKeyword())
        );

        Page<Book> bookPage = new Page<>(queryParams.getPage(), queryParams.getLimit());
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
        if (StringUtils.checkValNull(book)) throw new BusinessException("小说不存在");
        return ResponseDTO.succ(book);
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
    public ResponseDTO addBookComment(Comment comment)
    {
        Book book = bookMapper.selectById(comment.getResourceId());
        if(StringUtils.checkValNull(book)) throw new BusinessException("小说不存在");

        comment.setResourceType((byte) 1);
        if(this.existsBookComment(comment)) throw new BusinessException("已评价过该书籍！");

        commentMapper.insert(comment);
        return ResponseDTO.succ("评论成功");
    }

    @Override
    public ResponseDTO editBookComment(Long commentId, String content)
    {
        Comment comment = commentMapper.selectById(commentId);
        comment.setContent(content);
        commentMapper.updateById(comment);

        return ResponseDTO.succ(ResponseStatus.SUCCESS.getMsg());
    }

    @Override
    public boolean existsBookComment(Comment comment)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("resource_type", comment.getResourceType());
        map.put("resource_id", comment.getResourceId());
        map.put("user_id", comment.getUserId());
        List<Comment> bookComments = commentMapper.selectByMap(map);
        if (bookComments.isEmpty()) return false;
        else return true;
    }

}
