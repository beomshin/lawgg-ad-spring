package com.was.admin.modules.trialService.model.response;


import com.was.admin.common.dto.ResponseDto;
import com.was.admin.entities.TrialTb;
import com.was.admin.modules.trialService.model.dto.TrialProjectionDto;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
public class FindTrialListResponse extends ResponseDto {

    private List<TrialProjectionDto> list;
    private Long totalElements;
    private Integer totalPage;

    public FindTrialListResponse(Page<TrialProjectionDto> page) {
        this.list = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPage = page.getTotalPages();
    }
}
