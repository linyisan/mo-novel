package com.heng.service;

import com.heng.common.ResponseDTO;
import com.heng.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.vo.LoginVo;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
public interface UserService extends IService<User> {

    User getUserByUsername(String username);

    ResponseDTO login(LoginVo loginVo);

    ResponseDTO info(String token);

    ResponseDTO page(Integer pageNo, Integer pageSize);
}
