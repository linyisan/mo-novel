package com.heng.mapper;

import com.heng.dto.CategoryCountDO;
import com.heng.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
     * 查询小说集合
     * 按评分随机排序
     * @param limit 查询条数
     * @return 小说集合
     * */
    List<Book> selectByScoreAndRandom(Integer limit);

    /**
     * 通过小说分类查询
     * 按访问量随机排序
     * @param categoryIds
     * @param limit
     * @return
     */
    List<Book> selectByVisitCountAndAndRandom(@Param("categoryIds") List<Long> categoryIds, @Param("limit") Integer limit);

    /**
     * 按分类统计小说数量
     * @return
     */
    List<CategoryCountDO> selectGroupByCategory();
}
