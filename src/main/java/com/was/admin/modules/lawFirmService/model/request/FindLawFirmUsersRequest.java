package com.was.admin.modules.lawFirmService.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class FindLawFirmUsersRequest {

    private Integer page;
    private Integer pageNum;
    private Long id;

}
