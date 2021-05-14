package com.heng.mapper;

import com.heng.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 小说信息表 Mapper 接口
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
public interface BookMapper extends BaseMapper<Book> {
    /**
     * 按评分随机查询小说集合
     * @param limit 查询条数
     * @return 小说集合
     * */
    List<Book> selectByScoreAndRandom(Integer limit);
}
