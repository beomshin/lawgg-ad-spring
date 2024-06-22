package com.was.admin.modules.userService.service;

import com.was.admin.enums.element.flag.JudgeFlag;
import com.was.admin.enums.element.status.UserStatus;
import com.was.admin.modules.userService.dao.UserDao;
import com.was.admin.modules.userService.model.dto.UserDto;
import com.was.admin.repositories.UserTbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserDao userDao;
    private final UserTbRepository userTbRepository;

    @Override
    public Page findUsers(UserDto userDto) throws Exception {
        return userDao.findUsers(userDto, PageRequest.of(userDto.getPage(), userDto.getPageNum()));
    }

    @Override
    public void judgeUser(UserDto userDto) throws Exception {
        if (userDto.getType() == 1) {
            userTbRepository.updateJudge(userDto.getId(), JudgeFlag.USE_STATUS);
        } else {
            userTbRepository.updateJudge(userDto.getId(), JudgeFlag.NON_USE_STATUS);
        }
    }

    @Override
    public void statusUser(UserDto userDto) throws Exception {
        if (userDto.getType() == 0) {
            userTbRepository.updateStatus(userDto.getId(), UserStatus.REPORT_STATUS);
        } else {
            userTbRepository.updateStatus(userDto.getId(), UserStatus.NORMAL_STATUS);
        }
    }
}
