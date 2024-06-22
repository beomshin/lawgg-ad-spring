package com.was.admin.modules.lawFirmService.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data

public class FindLawFirmListRequest {

    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    private String keyword;

    private Integer type;
}
