package com.heng.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LJohn
 * @since 2021-05-15
 */
@SpringBootTest
class BookshelfServiceTest {
    @Autowired
    private BookshelfService bookshelfService;

    @Test
    void recommandByCategoriesPercent()
    {
        System.out.println("***********-*---------------------//////////////");
        bookshelfService.recommandByCategoriesPercent(5L, 100).forEach(System.out::println);
    }
}
