package com.heng.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heng.entity.User;
import com.heng.exception.BusinessException;
import com.heng.service.UserService;
import com.heng.util.JwtUtil;
import com.heng.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Slf4j
/**
 * 通过数据库查询并添加用户的授权认证到Subject
 */
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token)
    {
        return super.supports(token);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        log.debug("执行了Real授权");
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        // 设置角色
        HashSet<String> roles = new HashSet<>();
        if (user.getRoleId() == 1) roles.add("admin");
//        roles.addAll(user.getRoles());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);

        // 设置权限
        HashSet<String> permissions = new HashSet<>();
        simpleAuthorizationInfo.addStringPermissions(permissions);

//        return null;
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        log.debug("执行了Real认证");
        log.debug("subject.login(token);");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserInfoVo userInfoVo = JwtUtil.getUserInfoFormToken((String) token.getPrincipal());

//        User user = userService.getUserByUsername(token.getUsername());
        User user = userService.getUserByUsername(userInfoVo.getName());
        if (null != user)
        {
            log.info("第一个参数principalCollection可以传给授权(subject)");
            // 密码校验
            return new SimpleAuthenticationInfo(user, token.getPassword(), user.getUsername());
        }

        // 账号不存在
        return null;
    }

}
