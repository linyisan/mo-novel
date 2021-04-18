package com.heng.service;

import com.heng.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;

    @Test
    public void test()
    {
//        bookMapper.selectList(null).forEach(System.out::println);
        HashMap<String, Object> columnQueryMap = new HashMap<>();
        columnQueryMap.put("title", "%斗%");
//        bookService.listByMap(columnQueryMap);
        bookMapper.selectByMap(columnQueryMap);
    }
}
