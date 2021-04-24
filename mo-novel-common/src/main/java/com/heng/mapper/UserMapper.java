package com.heng.mapper;

import com.heng.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
public interface UserMapper extends BaseMapper<User> {

/*    @Results(id = "id", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "usernmae"),
            @Result(property = "roles", column = "role_id", one = @One(select = "com.heng.mapper.RoleMapper.selectById"))
    })
    @Select("select * from `t_user`")
    List<User> selectUserAndRole();*/
}
