package com.was.admin.modules.userService.model.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class FindUsersRequest {

    private Integer page;
    private Integer pageNum;
    private String keyword;
    private Integer statusType;
    private Integer snsType;
    private Integer orderType;

}
