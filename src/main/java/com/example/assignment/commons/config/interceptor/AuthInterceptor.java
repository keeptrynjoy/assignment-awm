package com.example.assignment.commons.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessDeniedException {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        String loginId = request.getParameter("loginId");

        return  authService.isAuthorized(loginId, auth);
    }
}
