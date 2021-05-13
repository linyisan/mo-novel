package com.heng.controller;


import com.heng.annotation.NeedAuth;
import com.heng.common.ResponseDTO;
import com.heng.common.ResponseStatus;
import com.heng.entity.Bookshelf;
import com.heng.service.BookshelfService;
import com.heng.util.MoRequestTokenUtil;
import com.heng.vo.BookshelfQueryVo;
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

    @NeedAuth
    @PostMapping("add")
    public ResponseDTO addBookshelf(@Validated @RequestBody Bookshelf bookshelf)
    {
        Long userId = MoRequestTokenUtil.getRequestUser().getId();
        bookshelf.setUserId(userId);
        bookshelfService.save(bookshelf);
        return ResponseDTO.succ("成功加入书架");
    }

    @GetMapping("delete/{bookshelfId}")
    public ResponseDTO deleteBookshelf(@PathVariable Long bookshelfId)
    {
        bookshelfService.removeById(bookshelfId);
        return ResponseDTO.succ("成功移除");
    }

    @PostMapping("edit")
    public ResponseDTO editBookshelf(@Validated @RequestBody Bookshelf bookshelf)
    {
        bookshelfService.updateById(bookshelf);
        return ResponseDTO.succ(ResponseStatus.SUCCESS.getMsg());
    }

    @GetMapping("search")
    public ResponseDTO searchBookshelf(BookshelfQueryVo bookshelfQueryVo)
    {
/*        userId = MoRequestTokenUtil.getRequestUser().getId();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);*/
        return bookshelfService.searchBookshelf(bookshelfQueryVo);
    }
}

