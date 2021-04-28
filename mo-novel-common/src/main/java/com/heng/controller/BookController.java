package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.heng.service.BookService;
import com.heng.vo.BookSpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 小说信息表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/searchByPage")
    public ResponseDTO searchByPage(@RequestParam(defaultValue = "1") Integer pageNo,
                            @RequestParam(defaultValue = "5") Integer pageSize,
                            BookSpVo queryParams)
    {
        return bookService.searchByPage(pageNo, pageSize, queryParams);
    }

    @PostMapping("add")
    public ResponseDTO add(Book book)
    {
        bookService.save(book);
        return ResponseDTO.succ("成功添加小说");
    }

}

