package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.common.ResponseStatus;
import com.heng.entity.Comment;
import com.heng.entity.Rating;
import com.heng.service.BookService;
import com.heng.service.CommentService;
import com.heng.service.RatingService;
import com.heng.valid.AddGroup;
import com.heng.vo.CommentQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 评价表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-05-01
 */
@RestController
public class CommentController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/bookcomment/add")
    public ResponseDTO addBookComment(@Validated({AddGroup.class}) @RequestBody Comment comment)
    {
        return bookService.addBookComment(comment);
    }

    @PostMapping("/bookcomment/edit")
    public ResponseDTO editBookComment(@RequestBody Comment comment)
    {
        return bookService.editBookComment(comment);
    }

    @GetMapping("/bookcomment/delete/{commentId}")
    public ResponseDTO deleteBookComment(@PathVariable Long commentId)
    {
        commentService.removeById(commentId);
        return ResponseDTO.succ(ResponseStatus.SUCCESS.getMsg());
    }

    @GetMapping("/bookcomment/getAll/{bookId}")
    public ResponseDTO getAllBookCommentByBookId(@PathVariable Long bookId)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("resource_type", 1);
        map.put("resource_id", bookId);
        List<Comment> bookComments = commentService.listByMap(map);
        return ResponseDTO.succ(bookComments);
    }


    @GetMapping("/bookcomment/search")
    public ResponseDTO searchBookComment(CommentQueryVo commentQueryVo)
    {
        commentQueryVo.setResourceType((byte)1);
        return commentService.searchBookComment(commentQueryVo);
    }
}

