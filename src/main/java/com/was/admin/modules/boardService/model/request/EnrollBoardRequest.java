package com.was.admin.modules.boardService.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EnrollBoardRequest {

    private String writer;
    private String title;
    private String content;
    private Integer type;
    private List<MultipartFile> files;

}
