package com.was.admin.modules.trialService.model.request;

import lombok.Data;

@Data
public class FindTrialListRequest {

    private Integer page;
    private Integer pageNum;
    private String keyword;
    private Integer type;
    private Integer liveType;
    private Integer endType;
    private Integer statusType;
}
