package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.common.ResponseDTO;
import com.heng.entity.BookSetting;
import com.heng.entity.Feedback;
import com.heng.mapper.FeedbackMapper;
import com.heng.service.FeedbackService;
import com.heng.vo.FeedbackQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 用户反馈 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-16
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public ResponseDTO searchFeedback(FeedbackQuery query)
    {
        QueryWrapper<Feedback> feedbackQueryWrapper = new QueryWrapper<>();
        feedbackQueryWrapper.eq(StringUtils.checkValNotNull(query.getUserId()), "user_id", query.getUserId())
                .eq(StringUtils.checkValNotNull(query.getDone()), "done", query.getDone())
                .like(StringUtils.isNotBlank(query.getContent()), "content", query.getContent());

        Page<Feedback> feedbackPage = new Page<>(query.getPage(), query.getLimit());
        feedbackMapper.selectPage(feedbackPage, feedbackQueryWrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", feedbackPage.getTotal());
        map.put("items", feedbackPage.getRecords());
        return ResponseDTO.succ(map);
    }
}
