package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.Bookshelf;
import com.heng.vo.BookshelfQueryVo;

/**
 * <p>
 * 用户书架 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface BookshelfService extends IService<Bookshelf> {
    public ResponseDTO searchBookshelf(BookshelfQueryVo bookshelfQueryVo);
}
