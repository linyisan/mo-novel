package com.heng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.dto.CategoryCountDO;
import com.heng.entity.Book;
import com.heng.entity.Bookshelf;

import java.util.List;

/**
 * <p>
 * 用户书架 Mapper 接口
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface BookshelfMapper extends BaseMapper<Bookshelf> {
    /**
     * 统计用户书架中的小说分类数量
     * @param userId 用户ID
     * @return
     */
    List<CategoryCountDO> selectCategoryJoinBookByUserId(Long userId);
}
