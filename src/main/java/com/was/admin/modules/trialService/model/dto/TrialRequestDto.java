package com.was.admin.modules.trialService.model.dto;

import com.was.admin.modules.boardService.model.request.UpdateTrialStatusRequest;
import com.was.admin.modules.trialService.model.EnrollTrialNoticeRequest;
import com.was.admin.modules.trialService.model.request.FindTrialDetailRequest;
import com.was.admin.modules.trialService.model.request.FindTrialListRequest;
import com.was.admin.modules.trialService.model.request.UpdateTrialMainRequest;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class TrialRequestDto {

    private Integer page;
    private Integer pageNum;
    private String keyword;
    private Integer type;
    private Integer liveType;
    private Integer endType;
    private Integer statusType;
    private Long id;
    private Integer status;
    private Integer postType;
    private Integer mainPostType;
    private String altVideoUrl;
    private Integer endingType;
    private Integer precedent;
    private Integer lawFirmDefense;
    private String writer;
    private String title;
    private String content;
    private List<MultipartFile> files;
    public TrialRequestDto(FindTrialDetailRequest request) {
        this.id = request.getId();
    }
    public TrialRequestDto(FindTrialListRequest request) {
        this.page = request.getPage();
        this.pageNum = request.getPageNum();
        this.keyword = request.getKeyword();
        this.type = request.getType();
        this.liveType = request.getLiveType();
        this.endType = request.getEndType();
        this.statusType = request.getStatusType();
    }

    public TrialRequestDto(UpdateTrialMainRequest request) {
        this.id = request.getId();
        this.type = request.getType();
    }

    public TrialRequestDto(UpdateTrialStatusRequest request) {
        this.id = request.getId();
        this.liveType = request.getLiveType();
        this.status = request.getStatus();
        this.postType = request.getPostType();
        this.mainPostType = request.getMainPostType();
        this.altVideoUrl = request.getAltVideoUrl();
        this.endingType = request.getEndingType();
        this.precedent = request.getPrecedent();
        this.lawFirmDefense = request.getLawFirmDefense();
    }

    public TrialRequestDto(EnrollTrialNoticeRequest request) {
        this.writer = request.getWriter();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.files = request.getFiles();
        this.type = request.getType();
    }
}
