package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.BookIndex;

/**
 * <p>
 * 小说目录表 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface BookIndexService extends IService<BookIndex> {
    ResponseDTO addBookContent(BookIndex bookIndex);

    ResponseDTO getBookContent(Long bookIndexId);

    ResponseDTO deleteBookContent(Long bookIndexId);

    ResponseDTO updateBookContent(BookIndex bookIndex);
}
