package com.heng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.entity.Rating;
import com.heng.vo.RatingQueryVo;

/**
 * <p>
 * 小说五星评分 Mapper 接口
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface RatingMapper extends BaseMapper<Rating> {
    Float avgBookScore(Long bookId);
}
