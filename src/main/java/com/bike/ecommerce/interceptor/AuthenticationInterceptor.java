package com.bike.ecommerce.interceptor;

import com.bike.ecommerce.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${api.auth.token}")
    private String secretToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Option to skip OPTIONS requests (pre-flight checks for browsers)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String authToken = request.getHeader("Authentication");

        if (authToken == null || !authToken.equals(secretToken)) {
            throw new UnauthorizedException("Invalid or missing Authentication token.");
        }

        return true;
    }
}
