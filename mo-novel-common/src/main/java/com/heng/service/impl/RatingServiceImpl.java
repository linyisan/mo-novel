package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.Rating;
import com.heng.mapper.RatingMapper;
import com.heng.service.RatingService;
import org.springframework.stereotype.Service;

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

}
