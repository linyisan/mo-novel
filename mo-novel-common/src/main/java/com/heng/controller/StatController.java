package com.heng.controller;

import com.heng.common.ResponseDTO;
import com.heng.service.BookService;
import com.heng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    /**
     * 表格总数
     * @return
     */
    @GetMapping("countSta")
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
        return ResponseDTO.succ(null);
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
