package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.common.ResponseStatus;
import com.heng.entity.Book;
import com.heng.entity.Comment;
import com.heng.service.BookService;
import com.heng.service.CommentService;
import com.heng.vo.BookSpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    private CommentService commentService;

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

    @PostMapping("addBookComment")
    public ResponseDTO addBookComment(@Validated Comment comment)
    {
        return bookService.addBookComment(comment);
    }

    @PostMapping("updateBookComment")
    public ResponseDTO updateBookComment(Long commentId, String content)
    {
        return bookService.updateBookComment(commentId, content);
    }

    @GetMapping("deleteBookComment/{commentId}")
    public ResponseDTO deleteBookComment(@PathVariable Long commentId)
    {
        commentService.removeById(commentId);
        return ResponseDTO.succ(ResponseStatus.SUCCESS.getMsg());
    }

    @GetMapping("pageBookComment/{bookId}")
    public ResponseDTO pageBookComment(@PathVariable Long bookId)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("resource_type", 1);
        map.put("resource_id", bookId);
        List<Comment> bookComments = commentService.listByMap(map);
        return ResponseDTO.succ(bookComments);
    }
}

