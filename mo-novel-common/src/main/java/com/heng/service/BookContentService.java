package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.BookContent;

/**
 * <p>
 * 小说章节内容表（mediumtext字段影响性能，从t_book_index表中分离） 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface BookContentService extends IService<BookContent> {
}
