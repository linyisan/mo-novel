package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.Like;
import com.heng.mapper.LikeMapper;
import com.heng.service.LikeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞踩表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-28
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

}
