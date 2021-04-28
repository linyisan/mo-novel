package com.heng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.entity.BookContent;

/**
 * <p>
 * 小说章节内容表（mediumtext字段影响性能，从t_book_index表中分离） Mapper 接口
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface BookContentMapper extends BaseMapper<BookContent> {

}
