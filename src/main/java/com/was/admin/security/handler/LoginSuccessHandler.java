package com.was.admin.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.was.admin.common.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("[LoginSuccessHandler] ==========> ");
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String userId = authentication.getName();
        String accessToken = jwtService.generateLoginAccessToken(userId, request.getRequestURI(), roles);
        Date expiredTime = jwtService.getLoginTokenExpiredTime(accessToken);
        String refreshToken = jwtService.generateLoginRefreshToken(userId, request.getRequestURI(), roles);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new HashMap<>();
        body.put("accessToken", accessToken);
        body.put("refreshToken", refreshToken);
        body.put("expiredTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiredTime));
        body.put("resultCode", "00000");

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
