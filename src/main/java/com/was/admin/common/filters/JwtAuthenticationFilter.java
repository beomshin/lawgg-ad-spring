package com.was.admin.common.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.was.admin.common.dto.GlobalCode;
import com.was.admin.common.dto.ResponseDto;
import com.was.admin.common.jwt.JwtService;
import com.was.admin.security.service.JwtDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtDetailService jwtDetailService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            String jwtToken = parseJwt(request);
            if (jwtToken != null && jwtService.validateLoginJwtToken(jwtToken)) {
                UserDetails userDetails = jwtDetailService.loadUserByUsername(jwtService.getLoginUserId(jwtToken));
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        }  catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | SignatureException e) {
            log.error("{}", e.getMessage());
            ResponseDto body = new ResponseDto();
            body.setError(GlobalCode.FAIL_GENERATE_TOKEN);
            response.setStatus(GlobalCode.EXPIRED_JWT_TOKEN.getHttpStatus().value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), body);
        } catch (ExpiredJwtException e) {
            log.error("{}", e.getMessage());
            ResponseDto body = new ResponseDto();
            body.setError(GlobalCode.EXPIRED_JWT_TOKEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(GlobalCode.EXPIRED_JWT_TOKEN.getHttpStatus().value());
            new ObjectMapper().writeValue(response.getOutputStream(), body);
        }
    }



    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
