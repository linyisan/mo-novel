package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.entity.Book;
import com.heng.entity.BookIndex;
import com.heng.entity.Rating;
import com.heng.mapper.BookMapper;
import com.heng.mapper.RatingMapper;
import com.heng.service.RatingService;
import com.heng.vo.RatingQueryVo;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 小说五星评分 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class RatingServiceImpl extends ServiceImpl<RatingMapper, Rating> implements RatingService {
    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Rating> searchRating(RatingQueryVo ratingQueryVo)
    {
        QueryWrapper<Rating> ratingQueryWrapper = new QueryWrapper<>();
        ratingQueryWrapper
                .eq(null != ratingQueryVo.getBookId(), "book_id", ratingQueryVo.getBookId())
                .eq(null != ratingQueryVo.getUserId(), "user_id", ratingQueryVo.getUserId())
                .eq(null != ratingQueryVo.getStar(), "start", ratingQueryVo.getStar());


        Page<Rating> ratingPage = new Page<>(ratingQueryVo.getPage(), ratingQueryVo.getLimit());
        ratingMapper.selectPage(ratingPage, ratingQueryWrapper);
        return ratingPage.getRecords();
    }

    @Override
    public Float avgBookScore(Long bookId)
    {
        return ratingMapper.avgBookScore(bookId);
    }

    @Transactional
    @Override
    public ResponseDTO addRating(Rating rating)
    {
        ratingMapper.insert(rating);

        changeBookScore(rating.getBookId());
        return ResponseDTO.succ("评分成功");
    }

    @Transactional
    @Override
    public ResponseDTO editRating(Rating rating)
    {
        ratingMapper.updateById(rating);

        changeBookScore(rating.getBookId());
        return ResponseDTO.succ("改分成功");
    }

    @Transactional
    @Override
    public ResponseDTO deleteRating(Long ratingId)
    {
        Long bookId = ratingMapper.selectById(ratingId).getBookId();
        ratingMapper.deleteById(ratingId);

        changeBookScore(bookId);
        return ResponseDTO.succ(null);
    }

    private boolean changeBookScore(Long bookId)
    {
        Book book = bookMapper.selectById(bookId);

        Float score = this.avgBookScore(book.getId());
        book.setScore(score);
        int isOk = bookMapper.updateById(book);
        return isOk > 0 ? true : false;
    }

}
