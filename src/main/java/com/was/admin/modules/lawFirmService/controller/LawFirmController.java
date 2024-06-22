package com.was.admin.modules.lawFirmService.controller;

import com.was.admin.common.dto.GlobalCode;
import com.was.admin.common.dto.ResponseDto;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmDto;
import com.was.admin.modules.lawFirmService.model.request.*;
import com.was.admin.modules.lawFirmService.model.response.FindLawFirmUsersResponse;
import com.was.admin.modules.lawFirmService.service.LawFirmService;
import com.was.admin.security.model.annotation.UserPrincipal;
import com.was.admin.security.model.dto.AdminAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LawFirmController {

    private final LawFirmService lawFirmService;

    @GetMapping("/api/law-firm/list")
    public ResponseEntity<ResponseDto> findLawFirmList(
            FindLawFirmListRequest requestDto
    ) throws Exception {
        ResponseDto body =  lawFirmService.findLawFirmList(new LawFirmDto(requestDto));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/law-firm/detail")
    public ResponseEntity<ResponseDto> findDetailLawFirm(
            @RequestParam(value = "id") Long id
    ) throws Exception {
        ResponseDto body =  lawFirmService.findDetailLawFirm(new LawFirmDto(id));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/law-firm/user")
    public ResponseEntity<ResponseDto> findUserLawFirm(
            FindUserListRequest request
    ) throws Exception {
        ResponseDto body =  lawFirmService.findUserLawFirm(new LawFirmDto(request));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/law-firm/enroll")
    public ResponseEntity<ResponseDto> enrollLawFirm(
            @ModelAttribute EnrollLawFirmRequest request,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        lawFirmService.enrollLawFirm(new LawFirmDto(request, adminAdapter));
        return ResponseEntity.ok(new ResponseDto());
    }

    @PostMapping("/api/law-firm/update")
    public ResponseEntity<ResponseDto> updateLawFirm(
            @ModelAttribute UpdateLawFirmRequest request,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        lawFirmService.updateLawFirm(new LawFirmDto(request, adminAdapter));
        return ResponseEntity.ok(new ResponseDto());
    }

    @PostMapping("/api/law-firm/status")
    public ResponseEntity<ResponseDto> updateStatusLawFirm(
            @RequestBody UpdateStatusLawFirmRequest request,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        lawFirmService.updateStatusLawFirm(new LawFirmDto(request, adminAdapter));
        return ResponseEntity.ok(new ResponseDto());
    }

    @GetMapping("/api/law-firm/users")
    public ResponseEntity<ResponseDto> findUsersLawFirm(
            @ModelAttribute FindLawFirmUsersRequest request,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        Page list = lawFirmService.findUsersLawFirm(new LawFirmDto(request, adminAdapter));
        return ResponseEntity.ok(new FindLawFirmUsersResponse(list));
    }

    @GetMapping("/api/law-firm/apply/users")
    public ResponseEntity<ResponseDto> findApplyUsersLawFirm(
            @ModelAttribute FindLawFirmUsersRequest request,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        Page list = lawFirmService.findApplyUsersLawFirm(new LawFirmDto(request, adminAdapter));
        return ResponseEntity.ok(new FindLawFirmUsersResponse(list));
    }

    @PostMapping("/api/law-firm/confirm")
    public ResponseEntity<ResponseDto> confirmLawFirm(
            @RequestBody ConfirmLawFirmRequestDto request,
            @UserPrincipal AdminAdapter adminAdapter
    ) throws Exception {
        ResponseDto body = new ResponseDto();
        boolean result = lawFirmService.confirmLawFirm(new LawFirmDto(request, adminAdapter));
        if (!result) body.setError(GlobalCode.ALREADY_APPLY_LAW_FIRM);
        return ResponseEntity.ok(body);
    }
}
