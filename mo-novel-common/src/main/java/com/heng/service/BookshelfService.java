package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.heng.entity.Bookshelf;
import com.heng.vo.BookshelfQueryVo;

import java.util.List;

/**
 * <p>
 * 用户书架 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface BookshelfService extends IService<Bookshelf> {
    ResponseDTO searchBookshelf(BookshelfQueryVo bookshelfQueryVo);

    /**
     * 通过书架的小说分类占比来推荐小说
     * @param userId
     * @param limit
     * @return
     */
    List<Book> recommandByCategoriesPercent(Long userId, Integer limit);
}
