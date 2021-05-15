package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.dto.CategoryCountDO;
import com.heng.entity.Book;
import com.heng.entity.Bookshelf;
import com.heng.entity.User;
import com.heng.mapper.BookMapper;
import com.heng.mapper.BookshelfMapper;
import com.heng.service.BookService;
import com.heng.service.BookshelfService;
import com.heng.vo.BookshelfQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private BookMapper bookMapper;

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

    @Override
    public List<Book> recommandByCategoriesPercent(Long userId, Integer limit)
    {
        List<CategoryCountDO> categoryCountDOs = bookshelfMapper.selectCategoryJoinBookByUserId(userId);

        List<Long> categoryIds;
        // 提取出list对象中的一个属性并去重
        if (categoryCountDOs.size() > 0)
        {
            categoryIds = categoryCountDOs.stream().map(CategoryCountDO::getCategoryId).distinct()
                    .limit(2)
                    .collect(Collectors.toList());
        }else
        {
            categoryIds = new ArrayList<>(0);
        }

        return bookMapper.selectByVisitCountAndAndRandom(categoryIds, limit);
    }
}
