package com.was.admin.modules.userService.model.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class JudgeUserRequest {

    private Long id;
    private Integer type;
}
