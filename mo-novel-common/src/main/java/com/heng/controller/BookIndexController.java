package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.BookIndex;
import com.heng.mapper.BookIndexMapper;
import com.heng.service.BookContentService;
import com.heng.service.BookIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 小说目录表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@RestController
@RequestMapping("/book-index")
public class BookIndexController {

    @Autowired
    private BookIndexMapper bookIndexMapper;

    @Autowired
    private BookIndexService bookIndexService;

    @Autowired
    private BookContentService bookContentService;

    @PostMapping("add")
    public ResponseDTO addBookContent(@Validated BookIndex bookIndex)
    {
        return bookIndexService.addBookContent(bookIndex);
//        bookContentService.saveOrUpdate()
    }

    @PostMapping("update")
    public ResponseDTO updateBookContent(@Validated BookIndex bookIndex)
    {
        return bookIndexService.updateBookContent(bookIndex);
    }

    @GetMapping("{bookIndexId}")
    public ResponseDTO getBookContent(@PathVariable Long bookIndexId)
    {
        return bookIndexService.getBookContent(bookIndexId);
    }

    @GetMapping("delete/{bookIndexId}")
    public ResponseDTO deleteBookContent(@PathVariable Long bookIndexId)
    {
        return bookIndexService.deleteBookContent(bookIndexId);
    }
}

