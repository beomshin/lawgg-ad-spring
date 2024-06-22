package com.was.admin.modules.boardService.service;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.exception.LgException;
import com.was.admin.modules.boardService.model.dto.BoardRequestDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardService {
    ResponseDto findAllListBoard(BoardRequestDto boardRequestDto) throws LgException;
    ResponseDto findBoardDetail(BoardRequestDto boardRequestDto) throws LgException;
    @Transactional
    void updateBoardStatus(BoardRequestDto boardRequestDto) throws LgException;
    @Transactional
    void updateBoardMain(BoardRequestDto boardRequestDto) throws LgException;
    @Transactional
    void enrollBoard(BoardRequestDto boardRequestDto) throws LgException;
}
