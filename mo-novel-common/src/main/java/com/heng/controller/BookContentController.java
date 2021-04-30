package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.common.ResponseStatus;
import com.heng.entity.BookContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 小说章节内容表（mediumtext字段影响性能，从t_book_index表中分离） 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@RestController
@RequestMapping("/book-content")
public class BookContentController {
}

