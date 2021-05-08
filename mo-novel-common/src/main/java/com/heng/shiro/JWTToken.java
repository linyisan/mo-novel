package com.heng.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author LJohn
 * @since 2021-05-06
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }


    public Object getPrincipal() {
        return token;
    }


    public Object getCredentials() {
        return token;
    }
}
