package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.Rating;
import com.heng.vo.RatingQueryVo;

import java.util.List;

/**
 * <p>
 * 小说五星评分 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
public interface RatingService extends IService<Rating> {

    List<Rating> searchRating(RatingQueryVo ratingQueryVo);
}
