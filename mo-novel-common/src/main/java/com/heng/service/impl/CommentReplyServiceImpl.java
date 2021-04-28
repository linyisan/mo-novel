package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.CommentReply;
import com.heng.mapper.CommentReplyMapper;
import com.heng.service.CommentReplyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评价/回复表	TODO:使用触发器维护用户头像，用户名字段 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements CommentReplyService {

}
