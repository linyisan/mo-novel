package com.heng.service.impl;

import com.heng.entity.Tag;
import com.heng.mapper.TagMapper;
import com.heng.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
