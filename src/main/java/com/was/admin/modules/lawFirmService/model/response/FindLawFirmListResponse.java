package com.was.admin.modules.lawFirmService.model.response;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.entities.LawFirmTb;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmProjection;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data

public class FindLawFirmListResponse extends ResponseDto {

    private List<LawFirmProjection> list;
    private Long totalElements;
    private Integer totalPage;

    public FindLawFirmListResponse(Page<LawFirmProjection> list) {
        this.list = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
    }
}
