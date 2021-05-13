package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.entity.Bookshelf;
import com.heng.entity.User;
import com.heng.mapper.BookshelfMapper;
import com.heng.service.BookshelfService;
import com.heng.vo.BookshelfQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 用户书架 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class BookshelfServiceImpl extends ServiceImpl<BookshelfMapper, Bookshelf> implements BookshelfService {
    @Autowired
    private BookshelfMapper bookshelfMapper;

    @Override
    public ResponseDTO searchBookshelf(BookshelfQueryVo bookshelfQueryVo)
    {
        QueryWrapper<Bookshelf> bookshelfQueryWrapper = new QueryWrapper<>();
        bookshelfQueryWrapper
                .eq(StringUtils.checkValNotNull(bookshelfQueryVo.getUserId()), "user_id", bookshelfQueryVo.getUserId())
                .eq(StringUtils.checkValNotNull(bookshelfQueryVo.getBookId()), "book_id", bookshelfQueryVo.getBookId())
//                .eq(null != bookshelfQueryVo.getReadingProcess(), "reading_process", bookshelfQueryVo.getReadingProcess())
                ;

        Page<Bookshelf> page = new Page<>(bookshelfQueryVo.getPage(), bookshelfQueryVo.getLimit());
        bookshelfMapper.selectPage(page, bookshelfQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", page.getRecords());
        map.put("total", page.getTotal());
//        map.put("item", userMapper.selectUserAndRole());
        return ResponseDTO.succ(map);
    }
}
