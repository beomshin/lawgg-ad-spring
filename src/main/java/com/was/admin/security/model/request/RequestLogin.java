package com.was.admin.security.model.request;

import lombok.Data;

@Data
public class RequestLogin {
    private String loginId;
    private String password;
}
