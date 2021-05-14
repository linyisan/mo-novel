package com.heng.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LJohn
 * @since 2021-05-14
 */
@SpringBootTest
class RatingMapperTest {
    @Autowired
    private RatingMapper ratingMapper;

    @Test
    void avgBookScore()
    {
        Float bookScore = ratingMapper.avgBookScore(1L);
        System.out.println("bookScore = " + bookScore);
    }
}
