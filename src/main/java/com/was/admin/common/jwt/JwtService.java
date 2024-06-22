package com.was.admin.common.jwt;

import java.util.Date;
import java.util.List;

public interface JwtService {

    String generateLoginAccessToken(String userId, String uri, List<String> roles);
    String generateLoginRefreshToken(String userId, String uri, List<String> roles);
    boolean validateLoginJwtToken(String token);
    Date getLoginTokenExpiredTime(String token);
    List<String> getLoginRoles(String token);
    String getLoginUserId(String token);
    String getLoginType(String token);

}
