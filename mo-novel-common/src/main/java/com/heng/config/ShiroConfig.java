package com.heng.config;

import com.heng.shiro.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                            ShiroFilterChainDefinition shiroFilterChainDefinition)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
        // 登录跳转
//        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权跳转
//        shiroFilterFactoryBean.setUnauthorizedUrl("/una");
        return shiroFilterFactoryBean;
    }

    @Bean("shiroFilterChainDefinition")
    public ShiroFilterChainDefinition getShiroFilterChainDefinition()
    {
        DefaultShiroFilterChainDefinition filterChainDefinition = new DefaultShiroFilterChainDefinition();
        // 权限设置
        HashMap<String, String> map = new HashMap<>();
//        map.put("/main", "authc");
//        map.put("/manage", "perms[manage]");
//        map.put("/admin/*", "roles[admin]");
        map.put("/**", "anon");
        filterChainDefinition.addPathDefinitions(map);
        return filterChainDefinition;
    }

    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AccountRealm accountRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);
        return securityManager;
    }

    @Bean(name = "accountRealm")
    public AccountRealm getAccountRealm()
    {
        return new AccountRealm();
    }
}
