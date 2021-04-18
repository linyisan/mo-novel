package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 小说信息 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer pageNo,
                            @RequestParam(defaultValue = "5") Integer pageSize)
    {
        return bookService.pageBook(pageNo, pageSize);
    }
}

