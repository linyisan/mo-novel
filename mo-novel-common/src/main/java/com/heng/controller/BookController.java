package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.common.ResponseStatus;
import com.heng.entity.Book;
import com.heng.entity.Comment;
import com.heng.service.BookService;
import com.heng.service.CommentService;
import com.heng.vo.BookQueryVo;
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

    @GetMapping("getAll")
    public ResponseDTO getAllBook()
    {
        List<Book> books = bookService.list();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", books.size());
        map.put("items", books);
        return ResponseDTO.succ(map);
    }

    @GetMapping("/search")
    public ResponseDTO searchBook(BookQueryVo queryParams)
    {
        return bookService.searchBook(queryParams);
    }

    @GetMapping("get/{bookId}")
    public ResponseDTO get(@PathVariable("bookId")Long bookId)
    {
        bookService.addVisitCount(bookId, 1L);
        return bookService.selectBookById(bookId);
    }

    @PostMapping("add")
    public ResponseDTO addBook(@Validated @RequestBody Book book)
    {
        bookService.save(book);
        return ResponseDTO.succ("成功添加小说");
    }

    @PostMapping("edit")
    public ResponseDTO editBook(@Validated @RequestBody Book book)
    {
        bookService.updateById(book);
        return ResponseDTO.succ("成功修改小说");
    }

    @GetMapping("delete/{bookId}")
    public ResponseDTO deleteBook(@PathVariable("bookId") Long bookId)
    {
        bookService.removeById(bookId);
        return ResponseDTO.succ("成功删除小说");
    }


    @PostMapping("addBookComment")
    public ResponseDTO addBookComment(@Validated Comment comment)
    {
        return bookService.addBookComment(comment);
    }

    @PostMapping("editBookComment")
    public ResponseDTO editBookComment(Long commentId, String content)
    {
        return bookService.editBookComment(commentId, content);
    }

    @GetMapping("deleteBookComment/{commentId}")
    public ResponseDTO deleteBookComment(@PathVariable Long commentId)
    {
        commentService.removeById(commentId);
        return ResponseDTO.succ(ResponseStatus.SUCCESS.getMsg());
    }

    @GetMapping("getBookCommentByBookId/{bookId}")
    public ResponseDTO getBookCommentByBookId(@PathVariable Long bookId)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("resource_type", 1);
        map.put("resource_id", bookId);
        List<Comment> bookComments = commentService.listByMap(map);
        return ResponseDTO.succ(bookComments);
    }
}

