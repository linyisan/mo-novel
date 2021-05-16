package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.entity.Notice;
import com.heng.mapper.NoticeMapper;
import com.heng.service.NoticeService;
import com.heng.vo.NoticeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 站内消息 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-16
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public ResponseDTO searchNotice(NoticeQuery query)
    {
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        noticeQueryWrapper.like(StringUtils.isNotBlank(query.getTitle()), "title", query.getTitle())
                .like(StringUtils.isNotBlank(query.getContent()), "content", query.getContent());

        Page<Notice> noticePage = new Page<>(query.getPage(), query.getLimit());
        noticeMapper.selectPage(noticePage, noticeQueryWrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", noticePage.getTotal());
        map.put("items", noticePage.getRecords());
        return ResponseDTO.succ(map);
    }
}
