package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.common.ResponseStatus;
import com.heng.entity.Bookshelf;
import com.heng.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户书架 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/bookshelf")
public class BookshelfController {
    @Autowired
    private BookshelfService bookshelfService;

    @PostMapping("add")
    public ResponseDTO add(@Validated Bookshelf bookshelf)
    {
        bookshelfService.saveOrUpdate(bookshelf);
        return ResponseDTO.succ("成功加入书架");
    }

    @GetMapping("delete/{bookshelfId}")
    public ResponseDTO delete(@PathVariable Long bookshelfId)
    {
        bookshelfService.removeById(bookshelfId);
        return ResponseDTO.succ("成功移除");
    }

    @PostMapping("update")
    public ResponseDTO update(@Validated Bookshelf bookshelf)
    {
        bookshelfService.updateById(bookshelf);
        return ResponseDTO.succ(ResponseStatus.SUCCESS.getMsg());
    }

    @GetMapping("list/{userId}")
    public ResponseDTO listByUserId(@PathVariable Long userId)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<Bookshelf> bookshelves = bookshelfService.listByMap(map);
        return ResponseDTO.succ(bookshelves);
    }
}

