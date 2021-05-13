package com.heng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.entity.Comment;
import com.heng.vo.CommentQueryVo;

import java.util.Set;

/**
 * <p>
 * 评价表 Mapper 接口
 * </p>
 *
 * @author LJohn
 * @since 2021-05-01
 */
public interface CommentMapper extends BaseMapper<Comment> {
    Set<Comment> selectAllCommentJoinRating(CommentQueryVo commentQueryVo);
}
