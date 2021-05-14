package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.dto.BookSettingQueryDTO;
import com.heng.entity.BookSetting;
import com.heng.service.BookService;
import com.heng.service.BookSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页小说设置表 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-05-15
 */
// TODO: test
@RestController
@RequestMapping("/booksetting")
public class BookSettingController {
    @Autowired
    private BookSettingService bookSettingService;

    @GetMapping("getAll")
    public ResponseDTO getAllBookSetting()
    {
        return ResponseDTO.succ(bookSettingService);
    }

    @GetMapping("get/{bookSettingId}")
    public ResponseDTO get(@PathVariable("bookSettingId") Long bookSettingId)
    {
        return ResponseDTO.succ(bookSettingService.getById(bookSettingId));
    }

    @PostMapping("search")
    public ResponseDTO searchBookSetting(BookSettingQueryDTO queryDTO)
    {
        return bookSettingService.searchBookSetting(queryDTO);
    }

    @PostMapping("add")
    public ResponseDTO addBookSetting(@Validated @RequestBody BookSetting bookSetting)
    {
        bookSettingService.save(bookSetting);
        return ResponseDTO.succ("添加首页展示小说成功");
    }

    @PostMapping("edit")
    public ResponseDTO editBookSetting(@Validated @RequestBody BookSetting bookSetting)
    {
        bookSettingService.updateById(bookSetting);
        return ResponseDTO.succ("修改首页展示小说成功");
    }

    @GetMapping("delete/{bookSettingId}")
    public ResponseDTO deleteBookSetting(@PathVariable("bookSettingId") Long bookSettingId)
    {
        bookSettingService.removeById(bookSettingId);
        return ResponseDTO.succ("删除首页展示小说成功");
    }
}

