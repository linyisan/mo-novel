package com.heng.mapper;

import com.heng.entity.BookIndex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LJohn
 * @since 2021-05-13
 */
@SpringBootTest
class BookIndexMapperTest {
    @Autowired
    private BookIndexMapper bookIndexMapper;
    @Test
    void selectLastBookIndex()
    {
        BookIndex bookIndex = bookIndexMapper.selectLastBookIndex(1L);
        System.out.println("bookIndex = " + bookIndex);
    }
}
