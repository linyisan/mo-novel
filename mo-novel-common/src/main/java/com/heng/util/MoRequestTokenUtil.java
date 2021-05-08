package com.heng.util;

import com.heng.entity.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 各个地方可调用
 * @author LJohn
 * @since 2021-05-08
 */
public class MoRequestTokenUtil {
    private static final String USER_KEY = "mo_user";

    private static ThreadLocal<User> RequestUserThreadLocal = new ThreadLocal<>();

    public static void setUser(HttpServletRequest request, User requestToken) {
        request.setAttribute(USER_KEY, requestToken);
        RequestUserThreadLocal.set(requestToken);
    }

    public static User getThreadLocalUser() {
        return RequestUserThreadLocal.get();
    }

    public static User getRequestUser() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                return (User) request.getAttribute(USER_KEY);
            }
        }
        return null;
    }

    public static Long getRequestUserId() {
        User requestUser = getRequestUser();
        if (null == requestUser) {
            return null;
        }
        return requestUser.getId();
    }
}
