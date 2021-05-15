package com.heng.service.impl;

import com.heng.common.ResponseDTO;
import com.heng.mapper.BookMapper;
import com.heng.service.BookService;
import com.heng.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LJohn
 * @since 2021-05-15
 */
public class StatServiceImpl implements StatService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseDTO fanSta()
    {
        Map<String, Object> fanStatMap = new HashMap<>();

        return null;
    }
}
