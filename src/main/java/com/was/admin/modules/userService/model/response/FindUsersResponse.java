package com.was.admin.modules.userService.model.response;

import com.was.admin.common.dto.ResponseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Slf4j
public class FindUsersResponse extends ResponseDto {

    private List list;
    private Long totalElements;
    private Integer totalPage;

    public FindUsersResponse(Page page) {
        this.list = page.getContent();
        this.totalElements = page.getTotalElements();;
        this.totalPage = page.getTotalPages();
    }

}
