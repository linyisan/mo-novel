package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.Constants;
import com.heng.common.ResponseDTO;
import com.heng.dto.BookSettingQuery;
import com.heng.entity.Book;
import com.heng.entity.BookSetting;
import com.heng.mapper.BookMapper;
import com.heng.mapper.BookSettingMapper;
import com.heng.service.BookSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 首页小说设置表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-15
 */
@Service
public class BookSettingServiceImpl extends ServiceImpl<BookSettingMapper, BookSetting> implements BookSettingService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookSettingMapper bookSettingMapper;

    /**
     * 初始化首页小说设置
     * 当数据库没有数据时
     */
    @Transactional
    private List<BookSetting> initIndexBookSetting()
    {
        List<Book> books = bookMapper.selectByScoreAndRandom(Constants.INDEX_BOOK_SETTING_NUM);
        if (books.size() != Constants.INDEX_BOOK_SETTING_NUM) return new ArrayList<>(0);

        List<BookSetting> bookSettingList = new ArrayList<>(Constants.INDEX_BOOK_SETTING_NUM);
        for (int i = 0; i < books.size(); i++)
        {
            Book book = books.get(i);

            // 分类
            byte type;
            if (i < 4) type = 1; // 轮播图
            else if (i < 10) type = 2; // 本周强推
            else if (i < 16) type = 3; // 热门推荐
            else type = 4; // 精品推荐

            // 填充
            BookSetting bookSetting = new BookSetting();
            bookSetting.setType(type);
            bookSetting.setSort((byte) i);
            bookSetting.setBookId(book.getId());
            bookSetting.setBook(book);

            bookSettingList.add(bookSetting);
        }

        // 批量持久化
        this.saveBatch(bookSettingList);
        return bookSettingList;
    }

    @Override
    public Map<Byte, List<BookSetting>> getAllBookSetting()
    {
        QueryWrapper<BookSetting> bookSettingQueryWrapper = new QueryWrapper<BookSetting>()
                .orderByAsc("sort");
        List<BookSetting> bookSettingList = this.list(bookSettingQueryWrapper);
        if (0 == bookSettingList.size())
        {
            //如果首页小说没有被设置(可以手动)，则自动初始化首页小说设置
            bookSettingList = initIndexBookSetting();
        }

        // 以Type分组
        Map<Byte, List<BookSetting>> result = bookSettingList.stream().collect(Collectors.groupingBy(BookSetting::getType));
        return result;
    }

    @Override
    public ResponseDTO searchBookSetting(BookSettingQuery query)
    {
        QueryWrapper<BookSetting> bookSettingQueryWrapper = new QueryWrapper<>();
        bookSettingQueryWrapper.eq(StringUtils.checkValNotNull(query.getBookId()), "book_id", query.getBookId())
                .eq(StringUtils.checkValNotNull(query.getType()), "type", query.getType())
                .eq(StringUtils.checkValNotNull(query.getSort()), "sort", query.getSort())
                .orderByAsc("sort");

        Page<BookSetting> bookSettingPage = new Page<>(query.getPage(), query.getLimit());
        bookSettingMapper.selectPage(bookSettingPage, bookSettingQueryWrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", bookSettingPage.getTotal());
        map.put("items", bookSettingPage.getRecords());
        return ResponseDTO.succ(map);
    }
}
