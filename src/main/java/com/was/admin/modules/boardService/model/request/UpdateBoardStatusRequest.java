package com.was.admin.modules.boardService.model.request;

import lombok.Data;

@Data
public class UpdateBoardStatusRequest {

    private Long id;
    private Integer status;
    private Integer type;
}
