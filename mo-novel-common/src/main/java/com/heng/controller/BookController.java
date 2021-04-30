package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.heng.entity.CommentReply;
import com.heng.service.BookService;
import com.heng.service.CommentReplyService;
import com.heng.vo.BookSpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @Autowired
    private CommentReplyService commentReplyService;

    @GetMapping("/searchByPage")
    public ResponseDTO searchByPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    BookSpVo queryParams)
    {
        return bookService.searchByPage(pageNo, pageSize, queryParams);
    }

    @PostMapping("addBook")
    public ResponseDTO addBook(Book book)
    {
        bookService.save(book);
        return ResponseDTO.succ("成功添加小说");
    }

    @GetMapping("{bookId}")
    public ResponseDTO bookDetail(@PathVariable("bookId")Long bookId)
    {
        bookService.addVisitCount(bookId, 1L);
        return bookService.selectBookById(bookId);
    }

    @PostMapping("addBookCommentReply")
    public ResponseDTO addBookCommentReply(CommentReply commentReply)
    {
        return bookService.addBookCommentReply(commentReply);
    }

    @GetMapping("pageBookCommentReply/{bookId}")
    public ResponseDTO pageBookCommentReply(@PathVariable Long bookId)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("resource_type", 1);
        map.put("resource_id", bookId);
        map.put("pid", 0);
        List<CommentReply> bookComments = commentReplyService.listByMap(map);
        return ResponseDTO.succ(bookComments);
    }
}

