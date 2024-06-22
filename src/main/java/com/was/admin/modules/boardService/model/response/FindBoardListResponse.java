package com.was.admin.modules.boardService.model.response;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.entities.BoardTb;
import com.was.admin.modules.boardService.model.dto.BoardProjection;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmProjection;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class FindBoardListResponse extends ResponseDto {


    private List<BoardProjection> list;
    private Long totalElements;
    private Integer totalPage;

    public FindBoardListResponse(Page<BoardProjection> list) {
        this.list = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
    }
}
