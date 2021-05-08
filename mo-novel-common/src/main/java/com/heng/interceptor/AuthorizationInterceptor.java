package com.heng.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.heng.annotation.IgnoreAuth;
import com.heng.annotation.NeedAuth;
import com.heng.entity.User;
import com.heng.exception.BusinessException;
import com.heng.service.UserService;
import com.heng.util.JwtUtil;
import com.heng.util.MoRequestTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    public static final String TOKEN_NAME = "X-Token";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {

/*        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,request-source,Token, Origin,imgType, Content-Type, cache-control,postman-token,Cookie, Accept,authorization");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (request.getMethod().equals(RequestMethod.OPTIONS.name()))
        {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }*/

        // 如果不是映射到方法直接通过
        boolean isHandlerMethod = handler instanceof HandlerMethod;
        if (! isHandlerMethod) {
            return true;
        }


        // 不需要登录注解
        boolean isIgnoreAuth = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class) != null;
        if (isIgnoreAuth)
        {
            return true;
        }

        // 不需要token
        boolean isNeedAuth = ((HandlerMethod) handler).getMethodAnnotation(NeedAuth.class) != null;
        if (!isNeedAuth)
        {
            return true;
        }

        // 需要做token校验, 消息头的token优先于请求query参数的token
        String xHeaderToken = request.getHeader(TOKEN_NAME);
        String xRequestToken = request.getParameter(TOKEN_NAME);
        String xAccessToken = null != xHeaderToken ? xHeaderToken : xRequestToken;
        if(StringUtils.isBlank(xAccessToken)) throw new BusinessException("需要登录");

        // JWT校验并得到用户信息
        Integer userId = (Integer) JwtUtil.getClaim(xAccessToken, JwtUtil.CLAIMS_KEY_USERID);
        User user = userService.getById(userId);
        if(null == user) throw new BusinessException("JWT无此用户");
        MoRequestTokenUtil.setUser(request, user);
        return true;

/*        TokenEntity tokenEntity = null;
        if(StringUtils.isNotBlank(token)) {
        	tokenEntity = tokenService.getTokenEntity(token);
        }

        if(tokenEntity != null) {
        	request.getSession().setAttribute("userId", tokenEntity.getUserid());
        	request.getSession().setAttribute("role", tokenEntity.getRole());
        	request.getSession().setAttribute("tableName", tokenEntity.getTablename());
        	request.getSession().setAttribute("username", tokenEntity.getUsername());
        	return true;
        }*/

/*        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try
        {
            writer = response.getWriter();
            writer.print("请先登录");
        } finally
        {
            if (writer != null)
            {
                writer.close();
            }
        }
//				throw new EIException("请先登录", 401);
        return false;*/
    }

}
