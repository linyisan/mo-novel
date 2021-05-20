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
     * 把某用户书架的所有小说进行根据类别进行分组
     * 若userID为空则统计所有用户书架
     * @param userId 用户ID
     * @return
     */
    List<CategoryCountDO> selectCategoryJoinBookByUserId(Long userId);

}
