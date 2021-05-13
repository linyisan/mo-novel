package com.heng.service;

import com.heng.common.ResponseDTO;
import com.heng.dto.UserUpdatePwdDTO;
import com.heng.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.vo.LoginVo;
import com.heng.vo.UserQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@Service
public interface UserService extends IService<User> {
    User getUserByUsername(String username);

    ResponseDTO login(LoginVo loginVo);

    ResponseDTO info(String token);

    ResponseDTO searchUser(UserQueryVo userQueryVo);

    ResponseDTO register(User user);

    ResponseDTO updatePwd(UserUpdatePwdDTO updatePwdDTO);
}
