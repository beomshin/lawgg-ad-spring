package com.was.admin.modules.lawFirmService.model.request;

import lombok.Data;

@Data
public class UpdateStatusLawFirmRequest {

    private Long id;
    private Integer status;
}
