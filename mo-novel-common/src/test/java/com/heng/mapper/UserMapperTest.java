package com.heng.mapper;

import com.heng.entity.User;
import com.heng.vo.UserInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LJohn
 * @since 2021-05-11
 */
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void selectUserInfoById()
    {
        UserInfoVo userInfoVo = userMapper.selectUserInfoById(5L);
        System.out.println("userInfoVo = " + userInfoVo);
    }

    @Test
    public void getUserRole()
    {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }
}
