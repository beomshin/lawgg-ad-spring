package com.was.admin.modules.trialService.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class EnrollTrialNoticeRequest {

    private String writer;
    private String title;
    private String content;
    private Integer type;
    private List<MultipartFile> files;

}
