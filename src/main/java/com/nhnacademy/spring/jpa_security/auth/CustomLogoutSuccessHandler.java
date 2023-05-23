package com.nhnacademy.spring.jpa_security.auth;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private final RedisTemplate<String, String> redisTemplate;

    public CustomLogoutSuccessHandler(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        HttpSession session = request.getSession(false);

        if (Objects.equals(redisTemplate.hasKey(session.getId()), Boolean.TRUE)) {
            redisTemplate.delete(session.getId());
        }
        session.invalidate();
        response.sendRedirect("/");
    }
}
