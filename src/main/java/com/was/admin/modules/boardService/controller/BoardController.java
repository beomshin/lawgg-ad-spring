package com.was.admin.modules.boardService.controller;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.modules.boardService.model.dto.BoardRequestDto;
import com.was.admin.modules.boardService.model.request.*;
import com.was.admin.modules.boardService.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/position/list")
    public ResponseEntity<ResponseDto> findBoardList(
            FindBoardListRequest requestDto
    ) throws Exception {
        ResponseDto body =  boardService.findAllListBoard(new BoardRequestDto(requestDto));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/position/detail")
    public ResponseEntity<ResponseDto> findBoardDetail(
            FindBoardDetailRequest requestDto
    ) throws Exception {
        ResponseDto body =  boardService.findBoardDetail(new BoardRequestDto(requestDto));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/position/status")
    public ResponseEntity<ResponseDto> updateBoardStatus(
            @RequestBody UpdateBoardStatusRequest requestDto
    ) throws Exception {
        boardService.updateBoardStatus(new BoardRequestDto(requestDto));
        return ResponseEntity.ok(new ResponseDto());
    }

    @PostMapping("/api/position/main")
    public ResponseEntity<ResponseDto> updateBoardMain(
            @RequestBody UpdateBoardMainRequest requestDto
    ) throws Exception {
        boardService.updateBoardMain(new BoardRequestDto(requestDto));
        return ResponseEntity.ok(new ResponseDto());
    }

    @PostMapping("/api/position/enroll")
    public ResponseEntity<ResponseDto> enrollBoard(
            @ModelAttribute EnrollBoardRequest requestDto
    ) throws Exception {
        boardService.enrollBoard(new BoardRequestDto(requestDto));
        return ResponseEntity.ok(new ResponseDto());
    }
}
