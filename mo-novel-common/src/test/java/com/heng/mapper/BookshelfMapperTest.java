package com.heng.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LJohn
 * @since 2021-05-15
 */
@SpringBootTest
class BookshelfMapperTest {
    @Autowired
    private BookshelfMapper bookshelfMapper;

    @Test
    void selectCategoryJoinBookByUserId()
    {
        System.out.println("****************************************////////////");
        bookshelfMapper.selectCategoryJoinBookByUserId(5L).forEach(System.out::println);
    }
}
