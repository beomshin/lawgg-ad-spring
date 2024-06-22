package com.was.admin.modules.lawFirmService.model.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class  UpdateLawFirmRequest {

    private Long id;

    private String title;

    private String introduction;

    private Long userId;

    private MultipartFile profile;

    private MultipartFile background;
    private Integer status;
    private String memo;
}
