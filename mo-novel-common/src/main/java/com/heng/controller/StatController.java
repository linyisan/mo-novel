package com.heng.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heng.common.ResponseDTO;
import com.heng.dto.CategoryCountDO;
import com.heng.entity.Book;
import com.heng.mapper.BookMapper;
import com.heng.mapper.BookshelfMapper;
import com.heng.service.BookService;
import com.heng.service.BookshelfService;
import com.heng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据统计
 * </p>
 *
 * @author LJohn
 * @since 2021-05-15
 */
@RestController
@RequestMapping("/stat")
public class StatController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookshelfMapper bookshelfMapper;

    @Autowired
    private BookMapper bookMapper;

    /**
     * 表格总数
     * @return
     */
    @GetMapping("fanSta2")
    public ResponseDTO countSta()
    {
        Map<String, Integer> map =  new HashMap<>();
        map.put("userCount", userService.count());
        map.put("bookCount", bookService.count());
        return ResponseDTO.succ(map);
    }

    /**
     * 扇形统计图
     * @return
     */
    @GetMapping("fanSta")
    public ResponseDTO fanSta()
    {
        Map<String, List<CategoryCountDO>> map =  new HashMap<>();
        map.put("categoryCountOfBook", bookMapper.selectGroupByCategory());
        map.put("categoryCountOfBookshelf", bookshelfMapper.selectCategoryJoinBookByUserId(null));

        return ResponseDTO.succ(map);
    }

    /**
     * 增量折线图
     * @return
     */
    @GetMapping("tableSta")
    public ResponseDTO tableSta()
    {
        return ResponseDTO.succ(null);
    }
}
