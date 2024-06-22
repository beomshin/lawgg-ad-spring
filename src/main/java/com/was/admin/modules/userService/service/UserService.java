package com.was.admin.modules.userService.service;

import com.was.admin.modules.userService.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserService {

    Page findUsers(UserDto userDto) throws Exception;

    @Transactional
    void judgeUser(UserDto userDto) throws Exception;

    @Transactional
    void statusUser(UserDto userDto) throws Exception;
}
