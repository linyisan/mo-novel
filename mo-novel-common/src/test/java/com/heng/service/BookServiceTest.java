package com.heng.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.heng.entity.Book;
import com.heng.entity.User;
import com.heng.mapper.BookMapper;
import com.heng.vo.BookQueryVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testWrapper()
    {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        BookQueryVo queryParams = new BookQueryVo()
                .setBookStatus((byte) 1)
                .setCategoryId(3)
                .setSort("update_time") // 配合下面orderByDesc
                .setKeyword("a");
/*        bookQueryWrapper.orderByDesc(StringUtils.hasText(queryParams.getSort()), queryParams.getSort())
                .eq("status", queryParams.getBookStatus());*/
        bookMapper.selectList(bookQueryWrapper).forEach(System.out::println);

    }

    @Test
    public void testJackson() throws JsonProcessingException
    {
        User user = new User().setId(1L)
                .setAvatar("https")
                .setEmail("") // JsonInclude.Include.NON_EMPTY
                .setCreateTime(LocalDateTime.now())
                .setMobile("123");
//        System.out.println("user = " + user);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // NULL 或 "" 字段不参与序列化
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        String json = objectMapper.writeValueAsString(user);
        Map<String, String> map = objectMapper.readValue(json, Map.class);
        System.out.println("json = " + json);
        System.out.println("map = " + map);
    }

    @Test
    public void testHultool()
    {
        User user = new User().setId(1L)
                .setAvatar("https")
                .setEmail("") // JsonInclude.Include.NON_EMPTY
                .setCreateTime(LocalDateTime.now())
                .setMobile("123");

    }

    @Test
    public void testMPStringUtils()
    {
        User user = new User().setId(1L)
                .setAvatar("https")
                .setEmail("") // JsonInclude.Include.NON_EMPTY
                .setCreateTime(LocalDateTime.now())
                .setMobile("123");
        String underline = StringUtils.camelToUnderline("updateTime");
        System.out.println("underline = " + underline);

        boolean checkValNotNull = StringUtils.checkValNotNull(user.getUpdateTime());
        System.out.println("checkValNotNull = " + checkValNotNull);
    }
}
