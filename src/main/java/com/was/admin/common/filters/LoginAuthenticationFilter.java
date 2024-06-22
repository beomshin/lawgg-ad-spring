package com.was.admin.common.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.was.admin.security.model.request.RequestLogin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, String filterProcessesUrl) {
        super();
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        this.setAuthenticationFailureHandler(authenticationFailureHandler);
        this.setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, BadCredentialsException {
        try {

            log.debug("[LoginAuthenticationFilter] ==========>");
            RequestLogin credential = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            if (StringUtils.isBlank(credential.getLoginId()) || StringUtils.isBlank(credential.getPassword())) {
                throw new AuthenticationServiceException("로그인 정보가 없습니다");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(credential.getLoginId(), credential.getPassword());

            return getAuthenticationManager().authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
