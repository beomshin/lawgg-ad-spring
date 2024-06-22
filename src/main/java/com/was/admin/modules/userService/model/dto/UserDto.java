package com.was.admin.modules.userService.model.dto;

import com.was.admin.modules.userService.model.request.FindUsersRequest;
import com.was.admin.modules.userService.model.request.JudgeUserRequest;
import com.was.admin.modules.userService.model.request.StatusUserRequest;
import com.was.admin.security.model.dto.AdminAdapter;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private Integer type;
    private Integer page;
    private Integer pageNum;
    private String keyword;
    private AdminAdapter adminAdapter;
    private Integer statusType;
    private Integer snsType;
    private Integer orderType;

    public UserDto(FindUsersRequest request, AdminAdapter adminAdapter) {
        this.page = request.getPage();
        this.pageNum = request.getPageNum();
        this.keyword = request.getKeyword();
        this.adminAdapter = adminAdapter;
        this.statusType = request.getStatusType();
        this.snsType = request.getSnsType();
        this.orderType = request.getOrderType();
    }

    public UserDto(JudgeUserRequest request, AdminAdapter adminAdapter) {
        this.id = request.getId();
        this.type = request.getType();
        this.adminAdapter = adminAdapter;
    }

    public UserDto(StatusUserRequest request, AdminAdapter adminAdapter) {
        this.id = request.getId();
        this.type = request.getType();
        this.adminAdapter = adminAdapter;
    }
}
