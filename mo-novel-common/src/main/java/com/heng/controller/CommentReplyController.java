package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.CommentReply;
import com.heng.exception.BusinessException;
import com.heng.service.CommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评价/回复表
TODO:使用触发器维护用户头像，用户名字段 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/comment-reply")
public class CommentReplyController {

/*    @Autowired
    private CommentReplyService commentReplyService;

    @PostMapping("add")
    private ResponseDTO add(CommentReply commentReply)
    {
        if (commentReplyService.save(commentReply))
        {
            return ResponseDTO.succ("成功添加评论");
        } else throw new BusinessException("你已经评论过");
    }*/

}

