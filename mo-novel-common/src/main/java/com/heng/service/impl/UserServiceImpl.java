package com.heng.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.common.ResponseDTO;
import com.heng.entity.Role;
import com.heng.entity.User;
import com.heng.mapper.UserMapper;
import com.heng.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.util.JwtUtil;
import com.heng.util.JwtUtils;
import com.heng.vo.LoginVo;
import com.heng.vo.UserInfoVo;
import com.heng.vo.UserQueryVo;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {
    @Autowired
    private UserMapper userMapper;

/*    @Autowired
    private PasswordEncoder passwordEncoder;*/

    @Override
    public User getUserByUsername(String username)
    {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        return user;
    }

    @Override
    public ResponseDTO login(LoginVo loginVo)
    {
        User user = getUserByUsername(loginVo.getUsername());
        if(user == null)
        {
            return ResponseDTO.fail("账户号码错误");
        }

        loginVo.setPassword(SecureUtil.md5(loginVo.getPassword()));
        if (!Objects.equals(loginVo.getPassword(), user.getPassword()))
        {
            return ResponseDTO.fail("密码错误");
        }


        if (user.getStatus() == 0)
        {
            return ResponseDTO.fail("账户禁用，请联系管理员");
        }

//        String token = "";//JwtUtils.sign(loginVo.getUsername(), "-1");
        String jwt = JwtUtil.generateToken(user.getUsername(), user.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO info(String token)
    {
        Integer userId = (Integer) JwtUtil.getClaim(token, JwtUtil.CLAIMS_KEY_USERID);
        User user = userMapper.selectById(userId);
        if(null == user) return ResponseDTO.fail("没有此账户");
        UserInfoVo userInfoVo = new UserInfoVo()
                .setId(user.getId())
                .setName(user.getUsername())
                .setAvatar(user.getAvatar())
                .setRoles(user.getRoles());

        return ResponseDTO.succ(userInfoVo);
    }

    @Override
    public ResponseDTO searchUser(UserQueryVo userQueryVo)
    {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(StringUtils.checkValNotNull(userQueryVo.getSex()), "sex", userQueryVo.getSex())
                .eq(StringUtils.checkValNotNull(userQueryVo.getRoleId()), "role_id", userQueryVo.getRoleId())
                .like(StringUtils.isNotBlank(userQueryVo.getUsername()), "username", userQueryVo.getUsername())
                .like(StringUtils.isNotBlank(userQueryVo.getEmail()), "email", userQueryVo.getEmail())
                .like(StringUtils.isNotBlank(userQueryVo.getMobile()), "mobile", userQueryVo.getMobile());

        Page<User> page = new Page<>(userQueryVo.getPage(), userQueryVo.getLimit());
        userMapper.selectPage(page, userQueryWrapper);
        HashMap<String, Object> map = new HashMap<>();

        map.put("items", page.getRecords());
        map.put("total", page.getTotal());
//        map.put("item", userMapper.selectUserAndRole());
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO register(User user)
    {
        if(null != getUserByUsername(user.getUsername()))
            return ResponseDTO.fail("用户名已经存在!");
        user.setPassword(SecureUtil.md5(user.getPassword()));
        if(0 >= userMapper.insert(user))
            return ResponseDTO.fail("注册失败");
        return ResponseDTO.succ("注册成功");
    }

}
