package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Feedback;
import com.heng.service.FeedbackService;
import com.heng.vo.FeedbackQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户反馈 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("get/{feedbackId}")
    public ResponseDTO get(@PathVariable("feedbackId") Long feedbackId)
    {
        return ResponseDTO.succ(feedbackService.getById(feedbackId));
    }

    @GetMapping("search")
    public ResponseDTO searchFeedback(FeedbackQuery query)
    {
        return feedbackService.searchFeedback(query);
    }

    @PostMapping("add")
    public ResponseDTO addFeedback(@Validated @RequestBody Feedback feedback)
    {
        feedbackService.save(feedback);
        return ResponseDTO.succ("添加反馈成功");
    }

    @PostMapping("edit")
    public ResponseDTO editFeedback(@Validated @RequestBody Feedback feedback)
    {
        feedbackService.updateById(feedback);
        return ResponseDTO.succ("修改反馈成功");
    }

    @GetMapping("delete/{feedbackId}")
    public ResponseDTO deleteFeedback(@PathVariable("feedbackId") Long feedbackId)
    {
        feedbackService.removeById(feedbackId);
        return ResponseDTO.succ("删除反馈成功");
    }

}

