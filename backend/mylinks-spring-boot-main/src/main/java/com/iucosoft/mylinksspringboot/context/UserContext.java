package com.iucosoft.mylinksspringboot.context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private static final ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> authoritiesThreadLocal = new ThreadLocal<>();

    public static Long getUserId() {
        return userIdThreadLocal.get();
    }

    public static void setUserId(Long userId) {
        userIdThreadLocal.set(userId);
    }

    public static void clear() {
        userIdThreadLocal.remove();
        authoritiesThreadLocal.remove();
    }

    public static String getAuthorities() {
        return authoritiesThreadLocal.get();
    }

    public static void setAuthorities(String authorities) {
        authoritiesThreadLocal.set(authorities);
    }
}

