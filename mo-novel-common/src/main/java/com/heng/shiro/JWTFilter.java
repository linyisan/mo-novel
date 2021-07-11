package com.heng.shiro;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LJohn
 * @since 2021-05-06
 */
@Component
public class JwtFilter extends AuthenticatingFilter {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 实现登录，我们需要生成我们自定义支持的JwtToken
     * 从请求头中获取jwt并登录
     * 交给executeLogin->subject.login->Realm
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        return StringUtils.isNotBlank(jwt) ? new JwtToken(jwt) : null;
    }

    /**
     * 拦截校验，当头部没有Authorization时候，我们直接通过，不需要自动登录；
     * 当带有的时候，首先我们校验jwt的有效性，
     * 没问题我们就直接执行executeLogin方法实现自动登录
     * @param servletRequest
     * @param servletResponse
     * @return jwt为空直接放行，不为空进行校验并登录
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isBlank(jwt)) return true;

        Claims claim = jwtUtil.getClaimByToken(jwt);
        if(null == claim || jwtUtil.isTokenExpired(claim.getExpiration()))
            throw new ExpiredCredentialsException("token已过期失效，请重新登录");

        // 执行登录
        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 拦截器的前置拦截，因为我们是前后端分析项目，项目中除了需要跨域全局配置之外，
     * 我们再拦截器中也需要提供跨域支持。这样，拦截器才不会在进入Controller之前就被限制了。
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
