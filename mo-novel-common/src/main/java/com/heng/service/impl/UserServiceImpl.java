package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.common.ResponseDTO;
import com.heng.entity.User;
import com.heng.mapper.UserMapper;
import com.heng.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.shiro.JwtUtil;
import com.heng.vo.LoginVo;
import com.heng.vo.UserInfoVo;
import com.heng.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

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

    @Autowired
    private JwtUtil jwtUtil;

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
            return ResponseDTO.fail("账户不存在");
        }

        if (!Objects.equals(loginVo.getPassword(), user.getPassword()))
        {
            return ResponseDTO.fail("密码错误");
        }


        if (user.getStatus() == 0)
        {
            return ResponseDTO.fail("账户禁用，请联系管理员");
        }

        String jwt = jwtUtil.generateToken(user.getId());

        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO info(String token)
    {
        UserInfoVo userInfoVo = new UserInfoVo(1L,
                "zhangsan",
                new String[]{"admin"},
                "https://sf3-ttcdn-tos.pstatp.com/img/user-avatar/0919a7c21d4dc335c27136900555d696~300x300.image"
                );
//        JwtUtils.verity(token);
        HashMap<String, Object> map = new HashMap<>();
        map.put(token, userInfoVo);
        return ResponseDTO.succ(map);
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
        if(0 >= userMapper.insert(user))
            return ResponseDTO.fail("注册失败");
        return ResponseDTO.succ("注册成功");
    }

}
