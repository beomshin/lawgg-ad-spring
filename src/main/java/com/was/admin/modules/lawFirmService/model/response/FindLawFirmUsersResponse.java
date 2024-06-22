package com.was.admin.modules.lawFirmService.model.response;

import com.was.admin.common.dto.ResponseDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@ToString
public class FindLawFirmUsersResponse extends ResponseDto {

    private List list;
    private Long totalElements;
    private Integer totalPage;


    public FindLawFirmUsersResponse(Page page) {
        this.list = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPage = page.getTotalPages();
    }
}
