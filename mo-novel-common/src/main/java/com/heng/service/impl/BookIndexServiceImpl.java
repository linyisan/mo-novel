package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.heng.entity.BookContent;
import com.heng.entity.BookIndex;
import com.heng.exception.BusinessException;
import com.heng.mapper.BookContentMapper;
import com.heng.mapper.BookIndexMapper;
import com.heng.mapper.BookMapper;
import com.heng.service.BookIndexService;
import com.heng.util.MStringUtil;
import com.heng.vo.BookIndexQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookIndexMapper bookIndexMapper;
    @Autowired
    private BookContentMapper bookContentMapper;

    @Override
    @Transactional
    public ResponseDTO addBookIndex(BookIndex bookIndex)
    {
        int wordCount = MStringUtil.getStrValidWordCount(bookIndex.getContent());

        // 更新小说信息表
        Book book = bookMapper.selectById(bookIndex.getBookId());
        if(StringUtils.checkValNull(book)) throw new BusinessException("小说不存在");
        book.setWordCount(book.getWordCount() + wordCount);
        bookMapper.updateById(book);

        // 更新小说目录表
//        System.out.println("插入前:" + bookIndex.getId());
        bookIndex.setWordCount(Long.valueOf(wordCount));
        bookIndexMapper.insert(bookIndex);
//        System.out.println("插入后:" + bookIndex.getId());

        // 更新小说内容表
        BookContent bookContent = new BookContent();
        bookContent.setIndexId(bookIndex.getId());
        bookContent.setContent(bookIndex.getContent());
        bookContentMapper.insert(bookContent);
        return ResponseDTO.succ("成功添加小说章节");
    }

    @Override
    @Transactional
    public ResponseDTO deleteBookIndex(Long bookIndexId)
    {
        BookIndex bookIndex = bookIndexMapper.selectById(bookIndexId);
        if(StringUtils.checkValNull(bookIndex)) throw new BusinessException("小说章节不存在");

        Long wordCount = bookIndex.getWordCount();
        // 更新目录表和内容表(外键)
        bookIndexMapper.deleteById(bookIndexId);

        // 更新小说信息表
        Book book = bookMapper.selectById(bookIndex.getBookId());
        book.setWordCount(book.getWordCount() - wordCount);
        bookMapper.updateById(book);

        return ResponseDTO.succ("小说章节删除成功");
    }

    @Override
    @Transactional
    public ResponseDTO editBookIndex(BookIndex bookIndex)
    {
        BookIndex oldBookIndex = bookIndexMapper.selectById(bookIndex.getId());
        if(StringUtils.checkValNull(oldBookIndex)) throw new BusinessException("小说章节不存在");

        Long wordCount = Long.valueOf(MStringUtil.getStrValidWordCount(bookIndex.getContent()));

        // 更新小说信息表
        Book book = bookMapper.selectById(oldBookIndex.getBookId());
        if(StringUtils.checkValNull(book)) throw new BusinessException("小说不存在");
        book.setWordCount(book.getWordCount() + wordCount - oldBookIndex.getWordCount());
        bookMapper.updateById(book);

        // 更新小说目录表
//        System.out.println("插入前:" + bookIndex.getId());
        bookIndex.setBookId(oldBookIndex.getBookId());
        bookIndex.setWordCount(wordCount);
        bookIndexMapper.updateById(bookIndex);
//        System.out.println("插入后:" + bookIndex.getId());

        // 更新小说内容表
        QueryWrapper<BookContent> bookContentQueryWrapper = new QueryWrapper<>();
        bookContentQueryWrapper.eq("index_id", bookIndex.getId());
        BookContent bookContent = bookContentMapper.selectOne(bookContentQueryWrapper);
        bookContent.setContent(bookIndex.getContent());
        bookContentMapper.updateById(bookContent);
        return ResponseDTO.succ("成功更新小说章节");
    }

    @Override
    public ResponseDTO searchBookIndex(BookIndexQueryVo bookIndexQueryVo)
    {
        QueryWrapper<BookIndex> bookIndexQueryWrapper = new QueryWrapper<>();
        bookIndexQueryWrapper.eq(StringUtils.checkValNotNull(bookIndexQueryVo.getBookId()), "book_id", bookIndexQueryVo.getBookId())
                .eq(StringUtils.checkValNotNull(bookIndexQueryVo.getStatus()), "status", bookIndexQueryVo.getStatus())
                .like(StringUtils.isNotBlank(bookIndexQueryVo.getTitle()), "title", bookIndexQueryVo.getTitle());

        Page<BookIndex> bookIndexPage = new Page<>(bookIndexQueryVo.getPage(), bookIndexQueryVo.getLimit());
        bookIndexMapper.selectPage(bookIndexPage, bookIndexQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", bookIndexPage.getRecords());
        map.put("total", bookIndexPage.getTotal());
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO getBookIndex(Long bookIndexId)
    {
        QueryWrapper<BookContent> bookContentQueryWrapper = new QueryWrapper<>();
        bookContentQueryWrapper.eq("index_id", bookIndexId);
        BookContent bookContent = bookContentMapper.selectOne(bookContentQueryWrapper);
        if(StringUtils.checkValNull(bookContent)) throw new BusinessException("该小说章节不存在");

        BookIndex bookIndex = bookIndexMapper.selectById(bookIndexId);
        bookIndex.setContent(bookContent.getContent());

        return ResponseDTO.succ(bookIndex);
    }
}
