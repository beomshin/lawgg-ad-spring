package com.was.admin.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.was.admin.common.dto.GlobalCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("[LoginFailHandler] =========> ");
        log.error("{}", exception.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, Object> result = new HashMap<>();
        GlobalCode globalCode = null;

        if (exception instanceof AuthenticationServiceException) {
            globalCode = GlobalCode.NOT_EXIST_USER;
        } else if(exception instanceof BadCredentialsException) {
            globalCode = GlobalCode.FAIL_LOGIN_INFO;
        } else if(exception instanceof LockedException) {
            globalCode = GlobalCode.LOCK_USER;
        } else if(exception instanceof DisabledException) {
            globalCode = GlobalCode.NOT_USE_USER;
        } else if(exception instanceof AccountExpiredException) {
            globalCode = GlobalCode.EXPIRED_USER;
        } else if(exception instanceof CredentialsExpiredException) {
            globalCode = GlobalCode.PASSWORD_EXPIRED_USER;
        } else {
            globalCode = GlobalCode.NOT_EXIST_USER;
        }

        result.put("resultCode", globalCode.getCode());
        result.put("resultMsg", globalCode.getMsg());

        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }
}
