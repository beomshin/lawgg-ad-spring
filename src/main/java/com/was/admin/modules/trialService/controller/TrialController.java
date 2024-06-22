package com.was.admin.modules.trialService.controller;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.modules.boardService.model.dto.BoardRequestDto;
import com.was.admin.modules.boardService.model.request.*;
import com.was.admin.modules.trialService.model.EnrollTrialNoticeRequest;
import com.was.admin.modules.trialService.model.dto.TrialRequestDto;
import com.was.admin.modules.trialService.model.request.FindTrialDetailRequest;
import com.was.admin.modules.trialService.model.request.FindTrialListRequest;
import com.was.admin.modules.trialService.model.request.UpdateTrialMainRequest;
import com.was.admin.modules.trialService.service.TrialService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TrialController {

    private final TrialService trialService;

    @GetMapping("/api/trial/list")
    public ResponseEntity<ResponseDto> findTrialList(
            FindTrialListRequest requestDto
    ) throws Exception {
        ResponseDto body =  trialService.findTrialList(new TrialRequestDto(requestDto));
        return ResponseEntity.ok(body);
    }


    @PostMapping("/api/trial/main")
    public ResponseEntity<ResponseDto> updateTrialMain(
            @RequestBody UpdateTrialMainRequest requestDto
    ) throws Exception {
        trialService.updateTrialMain(new TrialRequestDto(requestDto));
        return ResponseEntity.ok(new ResponseDto());
    }

    @GetMapping("/api/trial/detail")
    public ResponseEntity<ResponseDto> findTrialDetail(
            FindTrialDetailRequest request
    ) throws Exception {
        ResponseDto body = trialService.findTrialDetail(new TrialRequestDto(request));
        return ResponseEntity.ok(body);
    }


    @PostMapping("/api/trial/status")
    public ResponseEntity<ResponseDto> updateTrialStatus(
            @RequestBody UpdateTrialStatusRequest requestDto
    ) throws Exception {
        trialService.updateTrialStatus(new TrialRequestDto(requestDto));
        return ResponseEntity.ok(new ResponseDto());
    }
    
    @PostMapping("/api/trial/hot")
    public ResponseEntity<ResponseDto> updateTrialHot(
            @RequestBody UpdateTrialStatusRequest requestDto
    ) throws Exception {
        trialService.updateTrialHot(new TrialRequestDto(requestDto));
        return ResponseEntity.ok(new ResponseDto());
    }

    @PostMapping("/api/position/enroll/notice")
    public ResponseEntity<ResponseDto> enrollNoticeTrial(
            @ModelAttribute EnrollTrialNoticeRequest requestDto
    ) throws Exception {
        trialService.enrollNoticeTrial(new TrialRequestDto(requestDto));
        return ResponseEntity.ok(new ResponseDto());
    }
}
