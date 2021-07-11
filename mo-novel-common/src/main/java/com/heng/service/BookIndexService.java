package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.BookIndex;
import com.heng.vo.BookIndexQueryVo;

/**
 * <p>
 * 小说目录表 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface BookIndexService extends IService<BookIndex> {
    ResponseDTO addBookIndex(BookIndex bookIndex);

    ResponseDTO deleteBookIndex(Long bookIndexId);

    ResponseDTO editBookIndex(BookIndex bookIndex);

    ResponseDTO searchBookIndex(BookIndexQueryVo bookIndexQueryVo);

    ResponseDTO getBookIndex(Long bookIndexId);
}
