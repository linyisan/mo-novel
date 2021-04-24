package com.heng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heng.common.ResponseDTO;
import com.heng.entity.User;
import com.heng.mapper.UserMapper;
import com.heng.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.util.JwtUtils;
import com.heng.vo.LoginVo;
import com.heng.vo.UserInfoVo;
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

        if (!Objects.equals(loginVo.getPassword(), user.getPassword()))
        {
            return ResponseDTO.fail("密码错误");
        }


        if (user.getStatus() == 0)
        {
            return ResponseDTO.fail("账户禁用，请联系管理员");
        }

        String token = JwtUtils.sign(loginVo.getUsername(), "-1");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO info(String token)
    {
        UserInfoVo userInfoVo = new UserInfoVo(new String[]{"admin"},
                "I am a super administrator",
                "https://sf3-ttcdn-tos.pstatp.com/img/user-avatar/0919a7c21d4dc335c27136900555d696~300x300.image",
                "zhangsan");
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbk5hbWUiOiJhZG1pbiIsImV4cCI6MTYxODA2MDAzOSwidXNlcklkIjoiLTEifQ.E-MOPVBSIairTW3eIeV8GW1G4yo4tPPNJjCZIMN3VjM";
        JwtUtils.verity(token);
        HashMap<String, Object> map = new HashMap<>();
        map.put(token, userInfoVo);
        return ResponseDTO.succ(map);
    }

    @Override
    public ResponseDTO page(Integer pageNo, Integer pageSize)
    {
        Page<User> page = new Page<>(pageNo, pageSize);
        userMapper.selectPage(page, null);
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
