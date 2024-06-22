
package com.was.admin.modules.boardService.model.response;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.common.dto.FileResponse;
import com.was.admin.modules.boardService.model.dto.BoardProjection;
import lombok.Data;

import java.util.List;

@Data
public class FindBoardDetailResponse extends ResponseDto {

    private BoardProjection board;
    private List<FileResponse> files;

    public FindBoardDetailResponse(BoardProjection board, List<FileResponse> files) {
        this.board = board;
        this.files = files;
    }
}
