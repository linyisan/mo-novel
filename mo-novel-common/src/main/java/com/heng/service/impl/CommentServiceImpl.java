package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.entity.BookIndex;
import com.heng.entity.Comment;
import com.heng.entity.Rating;
import com.heng.mapper.BookMapper;
import com.heng.mapper.CommentMapper;
import com.heng.service.CommentService;
import com.heng.service.RatingService;
import com.heng.vo.CommentQueryVo;
import com.heng.vo.RatingQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 评价表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RatingService ratingService;

    @Override
    public List<Comment> searchComment(CommentQueryVo commentQueryVo)
    {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper
                .eq(null != commentQueryVo.getResourceType(), "resource_type", commentQueryVo.getResourceType())
                .eq(null != commentQueryVo.getResourceId(), "resource_id", commentQueryVo.getResourceId())
                .eq(null != commentQueryVo.getUserId(), "user_id", commentQueryVo.getUserId())
                .eq(null != commentQueryVo.getContent(), "content", commentQueryVo.getContent());

        Page<Comment> commentPage = new Page<>(commentQueryVo.getPage(), commentQueryVo.getLimit());
        commentMapper.selectPage(commentPage, commentQueryWrapper);
        return commentPage.getRecords();
    }

    @Override
    @Transactional
    public ResponseDTO searchBookComment(CommentQueryVo commentQueryVo)
    {
        List<Comment> comments = this.searchComment(commentQueryVo);

        // star
        RatingQueryVo ratingQueryVo = new RatingQueryVo();
        comments.forEach(comment -> {
            ratingQueryVo.setBookId(commentQueryVo.getResourceId());
            ratingQueryVo.setUserId(commentQueryVo.getUserId());
            comment.setBook(bookMapper.selectById(comment.getResourceId()));

            List<Rating> ratings = ratingService.searchRating(ratingQueryVo);
            if (!ratings.isEmpty())
            {
                comment.setStar(ratings.get(0).getStar());
                comment.setRating(ratings.get(0));
            }
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", comments);
        map.put("total", comments.size());
        return ResponseDTO.succ(map);
    }
}
