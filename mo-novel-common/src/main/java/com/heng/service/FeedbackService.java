package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.Feedback;
import com.heng.vo.FeedbackQuery;

/**
 * <p>
 * 用户反馈 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-16
 */
public interface FeedbackService extends IService<Feedback> {

    ResponseDTO searchFeedback(FeedbackQuery query);
}
