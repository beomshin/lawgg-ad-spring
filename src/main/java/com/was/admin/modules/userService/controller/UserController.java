package com.was.admin.modules.userService.controller;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.modules.boardService.model.request.FindBoardListRequest;
import com.was.admin.modules.userService.model.dto.UserDto;
import com.was.admin.modules.userService.model.request.FindUsersRequest;
import com.was.admin.modules.userService.model.request.JudgeUserRequest;
import com.was.admin.modules.userService.model.request.StatusUserRequest;
import com.was.admin.modules.userService.model.response.FindUsersResponse;
import com.was.admin.modules.userService.service.UserService;
import com.was.admin.security.model.annotation.UserPrincipal;
import com.was.admin.security.model.dto.AdminAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user/list")
    public ResponseEntity<ResponseDto> findUsers(
            FindUsersRequest requestDto,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        Page page =  userService.findUsers(new UserDto(requestDto, adminAdapter));
        return ResponseEntity.ok(new FindUsersResponse(page));
    }

    @PostMapping("/api/user/judge")
    public ResponseEntity<ResponseDto> judgeUser(
            @RequestBody JudgeUserRequest requestDto,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        userService.judgeUser(new UserDto(requestDto, adminAdapter));
        return ResponseEntity.ok(new ResponseDto());
    }

    @PostMapping("/api/user/status")
    public ResponseEntity<ResponseDto> statusUser(
            @RequestBody StatusUserRequest requestDto,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        userService.statusUser(new UserDto(requestDto, adminAdapter));
        return ResponseEntity.ok(new ResponseDto());
    }
}
