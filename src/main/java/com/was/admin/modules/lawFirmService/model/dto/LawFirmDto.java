package com.was.admin.modules.lawFirmService.model.dto;

import com.was.admin.modules.lawFirmService.model.request.*;
import com.was.admin.security.model.dto.AdminAdapter;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
public class LawFirmDto {

    private Long id;
    private String title;
    private String introduction;
    private Long userId;
    private MultipartFile profile;
    private MultipartFile background;
    private AdminAdapter adminAdapter;
    private Integer page;
    private Integer pageNum;
    private String keyword;
    private Integer status;
    private Integer type;
    private String memo;
    private Integer accept; // 2: 미수용, 1: 수용

    public LawFirmDto(Long id) {
        this.id = id;
    }

    public LawFirmDto(FindUserListRequest request) {
        this.page = request.getPage();
        this.pageNum = request.getPageNum();
        this.keyword = request.getKeyword();
    }

    public LawFirmDto(EnrollLawFirmRequest request, AdminAdapter adminAdapter) {
        this.title = request.getTitle();
        this.introduction = request.getIntroduction();
        this.userId = request.getUserId();
        this.profile = request.getProfile();
        this.background = request.getBackground();
        this.adminAdapter = adminAdapter;
        this.memo = request.getMemo();
    }

    public LawFirmDto(UpdateLawFirmRequest request, AdminAdapter adminAdapter) {
        this.id = request.getId();
        this.title = request.getTitle();
        this.introduction = request.getIntroduction();
        this.userId = request.getUserId();
        this.profile = request.getProfile();
        this.background = request.getBackground();
        this.adminAdapter = adminAdapter;
        this.status = request.getStatus();
        this.memo = request.getMemo();
    }

    public LawFirmDto (FindLawFirmListRequest request) {
        this.page = request.getPage();
        this.pageNum = request.getPageNum();
        this.keyword = request.getKeyword();
        this.type = request.getType();
    }

    public LawFirmDto (UpdateStatusLawFirmRequest request, AdminAdapter adminAdapter) {
        this.id = request.getId();
        this.adminAdapter = adminAdapter;
        this.status = request.getStatus();
    }

    public LawFirmDto(FindLawFirmUsersRequest request, AdminAdapter adminAdapter) {
        this.id = request.getId();
        this.page = request.getPage();
        this.pageNum = request.getPageNum();
        this.adminAdapter = adminAdapter;
    }

    public LawFirmDto(ConfirmLawFirmRequestDto requestDto, AdminAdapter adminAdapter) {
        this.id = requestDto.getId();
        this.accept = requestDto.getAccept();
        this.adminAdapter = adminAdapter;
    }
}
