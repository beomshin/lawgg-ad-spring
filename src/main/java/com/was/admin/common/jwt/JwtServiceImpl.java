package com.was.admin.common.jwt;

import com.was.admin.common.crypto.KeyManager;
import com.was.admin.common.utils.JwtTokenProvider;
import com.was.admin.enums.element.type.SnsType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService{

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${token.access-expired-time}")
    private long ACCESS_EXPIRED_TIME;

    @Value("${token.refresh-expired-time}")
    private long REFRESH_EXPIRED_TIME;

    @Override
    public String generateLoginAccessToken(String userId, String uri, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        claims.put("type", SnsType.LG_SNS_TYPE.getCode());
        return jwtTokenProvider.createJwtToken(KeyManager.jwtKey, claims, uri, new Date(System.currentTimeMillis() + (ACCESS_EXPIRED_TIME * 1000)));
    }

    @Override
    public String generateLoginRefreshToken(String userId, String uri, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        claims.put("type", SnsType.LG_SNS_TYPE.getCode());
        return jwtTokenProvider.createJwtToken(KeyManager.jwtKey, claims, uri, new Date(System.currentTimeMillis() + (REFRESH_EXPIRED_TIME * 1000)));
    }

    @Override
    public boolean validateLoginJwtToken(String token) {
        return jwtTokenProvider.validateJwtToken(KeyManager.jwtKey, token);
    }

    @Override
    public Date getLoginTokenExpiredTime(String token) {
        return jwtTokenProvider.getExpiredTime(KeyManager.jwtKey, token);
    }

    @Override
    public List<String> getLoginRoles(String token) {
        return jwtTokenProvider.getRoles(KeyManager.jwtKey, token);
    }

    @Override
    public String getLoginUserId(String token) {
        return jwtTokenProvider.getUserId(KeyManager.jwtKey, token);
    }

    @Override
    public String getLoginType(String token) {
        return jwtTokenProvider.getType(KeyManager.jwtKey, token);
    }
}
