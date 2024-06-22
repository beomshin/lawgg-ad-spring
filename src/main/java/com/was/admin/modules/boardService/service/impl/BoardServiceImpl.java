package com.was.admin.modules.boardService.service.impl;

import com.was.admin.common.dto.FileDto;
import com.was.admin.common.dto.GlobalCode;
import com.was.admin.common.dto.ResponseDto;
import com.was.admin.common.file.FileService;
import com.was.admin.entities.BoardAttachTb;
import com.was.admin.entities.BoardCommentTb;
import com.was.admin.entities.BoardTb;
import com.was.admin.entities.MainBoardTb;
import com.was.admin.enums.element.etc.Depth;
import com.was.admin.exception.LgException;
import com.was.admin.modules.boardService.dao.BoardDao;
import com.was.admin.common.dto.FileResponse;
import com.was.admin.modules.boardService.model.dto.BoardProjection;
import com.was.admin.modules.boardService.model.dto.BoardRequestDto;
import com.was.admin.modules.boardService.model.response.FindBoardDetailResponse;
import com.was.admin.modules.boardService.service.BoardService;
import com.was.admin.modules.boardService.model.response.FindBoardListResponse;
import com.was.admin.repositories.BoardAttachTbRepository;
import com.was.admin.repositories.BoardCommentTbRepository;
import com.was.admin.repositories.BoardTbRepository;
import com.was.admin.repositories.MainBoardTbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final BoardTbRepository boardTbRepository;
    private final BoardAttachTbRepository boardAttachTbRepository;
    private final MainBoardTbRepository mainBoardTbRepository;
    private final BoardCommentTbRepository boardCommentTbRepository;

    private final FileService<FileDto> fileService;

    @Override
    public ResponseDto findAllListBoard(BoardRequestDto boardRequestDto) throws LgException {
        Page<BoardProjection> boardTbs = boardDao.findBoardList(boardRequestDto, PageRequest.of(boardRequestDto.getPage(), boardRequestDto.getPageNum()));
        return new FindBoardListResponse(boardTbs);
    }

    @Override
    public ResponseDto findBoardDetail(BoardRequestDto boardRequestDto) throws LgException {
        BoardProjection board = boardDao.findBoardDetail(boardRequestDto);
        List<FileResponse> attachTbs = boardAttachTbRepository.findByBoardId_BoardId(board.getBoardId()).stream().map(FileResponse::new).collect(Collectors.toList());
        return new FindBoardDetailResponse(board, attachTbs);
    }

    @Override
    public void updateBoardStatus(BoardRequestDto boardRequestDto) throws LgException {
        BoardTb boardTb = boardTbRepository.findById(boardRequestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD));
        boardTb.updateStatus(boardRequestDto.getStatus(), boardRequestDto.getType());
    }

    @Override
    public void updateBoardMain(BoardRequestDto boardRequestDto) throws LgException {
        BoardTb boardTb = boardTbRepository.findById(boardRequestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD));
        if (boardRequestDto.getType() == 0) {
            mainBoardTbRepository.save(new MainBoardTb(boardTb));
        } else {
            MainBoardTb mainBoardTb = mainBoardTbRepository.findByBoardId(boardTb.getBoardId());
            mainBoardTbRepository.deleteById(mainBoardTb.getMainBoardId());
        }
    }

    @Override
    public void enrollBoard(BoardRequestDto boardRequestDto) throws LgException {
    	
        BoardTb boardTb = boardTbRepository.save(new BoardTb(boardRequestDto)); // 게시판 저장
        
        if (boardRequestDto.getFiles() != null || boardRequestDto.getFiles().size()  > 0 ) {
        	String path = this.addFiles(boardRequestDto.getFiles(), boardTb);
        	String newContents = "<img src=\""+path+"\" style=\"width: 800px;\"><br>"+boardTb.getContent();
        	boardTbRepository.updateBoard(boardTb.getBoardId(), boardTb.getTitle(), newContents);
        }
        
        boardCommentTbRepository.save(new BoardCommentTb(boardTb, Depth.ROOT_COMMENT)); // 루트 댓글 저장
    }

    @Transactional
    public String addFiles(List<MultipartFile> files, BoardTb boardTb) { // 파일 저장
        List<BoardAttachTb> boardAttachTbs = fileService.uploadMultiple(files).stream().filter(it -> it != null)
                .map(it -> new BoardAttachTb(boardTb, it)).collect(Collectors.toList());
        boardAttachTbRepository.saveAll(boardAttachTbs);
        return boardAttachTbs.get(0).getPath();
    }
}
