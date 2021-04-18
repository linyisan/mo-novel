package com.heng.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heng.entity.User;
import com.heng.service.UserService;
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
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        logger.debug("执行了Real授权");
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        // 设置角色
        HashSet<String> roles = new HashSet<>();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);

        // 设置权限
//        simpleAuthorizationInfo.addStringPermissions();

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        logger.debug("执行了Real认证");
        logger.debug("subject.login(token);");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.getUserByUsername(token.getUsername());
        if (null != user)
        {
            logger.info("第一个参数principalCollection可以传给授权");
            return new SimpleAuthenticationInfo(user, user.getPassword(), user.getUsername());
        }

        return null;
    }

}
