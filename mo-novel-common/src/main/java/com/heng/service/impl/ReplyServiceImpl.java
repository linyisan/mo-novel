package com.heng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.entity.Reply;
import com.heng.mapper.ReplyMapper;
import com.heng.service.ReplyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 回复表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-01
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

}
