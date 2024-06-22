package com.was.admin.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtTokenProvider {

    private final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    public String createJwtToken(String key, Claims claims, String uri, Date expiration) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(expiration)
                .setIssuedAt(new Date())
                .signWith(ALGORITHM, key)
                .setIssuer(uri)
                .compact();
    }

    public boolean  validateJwtToken(String key, String token) {
        Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        return true;
    }


    private Claims getClaimsFromJwtToken(String key, String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Date getExpiredTime(String key, String accessToken) {
        return getClaimsFromJwtToken(key, accessToken).getExpiration();
    }

    public String getUserId(String key, String token) {
        return getClaimsFromJwtToken(key, token).getSubject();
    }

    public String getType(String key, String token) {
        return getClaimsFromJwtToken(key,token).get("type").toString();
    }

    public List<String> getRoles(String key, String token) {
        return (List<String>) getClaimsFromJwtToken(key, token).get("roles");
    }

}
