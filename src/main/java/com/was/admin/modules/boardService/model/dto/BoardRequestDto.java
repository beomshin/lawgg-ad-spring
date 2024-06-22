package com.was.admin.modules.boardService.model.dto;

import com.was.admin.modules.boardService.model.request.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardRequestDto {

    private Long id;
    private Integer page;
    private Integer pageNum;
    private String keyword;
    private Integer type;
    private Integer type2;
    private Integer status;
    private Integer type3;
    private String writer;
    private String title;
    private String content;
    private List<MultipartFile> files;

    public BoardRequestDto(FindBoardListRequest request) {
        this.page = request.getPage();
        this.pageNum = request.getPageNum();
        this.keyword = request.getKeyword();
        this.type = request.getType();
        this.type2 = request.getType2();
        this.type3 = request.getType3();
    }

    public BoardRequestDto(FindBoardDetailRequest request) {
        this.id = request.getId();
    }

    public BoardRequestDto(UpdateBoardStatusRequest request) {
        this.id = request.getId();
        this.status = request.getStatus();
        this.type = request.getType();
    }

    public BoardRequestDto(UpdateBoardMainRequest request) {
        this.id = request.getId();
        this.type = request.getType();
    }

    public BoardRequestDto(EnrollBoardRequest request) {
        this.writer = request.getWriter();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.type = request.getType();
        this.files = request.getFiles();
    }
}
