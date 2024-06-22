package com.was.admin.modules.boardService.model.request;

import lombok.Data;

@Data
public class UpdateTrialStatusRequest {

    private Long id;
    private Integer status;
    private Integer postType;
    private Integer mainPostType;
    private Integer liveType;
    private Integer endingType;
    private Integer precedent;
    private Integer lawFirmDefense;
    private String altVideoUrl;

}
