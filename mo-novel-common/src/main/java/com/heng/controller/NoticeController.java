package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Notice;
import com.heng.service.NoticeService;
import com.heng.vo.NoticeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 站内消息 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("get/{noticeId}")
    public ResponseDTO get(@PathVariable("noticeId") Long noticeId)
    {
        return ResponseDTO.succ(noticeService.getById(noticeId));
    }

    @GetMapping("search")
    public ResponseDTO searchNotice(NoticeQuery query)
    {
        return noticeService.searchNotice(query);
    }

    @PostMapping("add")
    public ResponseDTO addNotice(@Validated @RequestBody Notice notice)
    {
        noticeService.save(notice);
        return ResponseDTO.succ("添加站内消息成功");
    }

    @PostMapping("edit")
    public ResponseDTO editNotice(@Validated @RequestBody Notice notice)
    {
        noticeService.updateById(notice);
        return ResponseDTO.succ("修改站内消息成功");
    }

    @GetMapping("delete/{noticeId}")
    public ResponseDTO deleteNotice(@PathVariable("noticeId") Long noticeId)
    {
        noticeService.removeById(noticeId);
        return ResponseDTO.succ("删除站内消息成功");
    }

}

