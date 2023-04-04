package com.example.pccp.filter;

import com.example.pccp.exception.PccpException;
import com.example.pccp.exception.PccpExceptionEnum;
import com.example.pccp.util.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if(token.isEmpty()){
            throw new PccpException(PccpExceptionEnum.TOKEN_EMPTY);
        }
        try {
            JwtUtils.verify(token);
        } catch (Exception e) {
            throw new PccpException(PccpExceptionEnum.TOKEN_PAST);
        }
        return true;
    }
}
