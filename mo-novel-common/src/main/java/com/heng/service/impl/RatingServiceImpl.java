package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.entity.BookIndex;
import com.heng.entity.Rating;
import com.heng.mapper.RatingMapper;
import com.heng.service.RatingService;
import com.heng.vo.RatingQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
