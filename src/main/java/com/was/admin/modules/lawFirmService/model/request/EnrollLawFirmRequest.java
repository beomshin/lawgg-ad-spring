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
public class EnrollLawFirmRequest {

    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @NotNull(message = "가입신청글이 입력되어있지않습니다.")
    private String introduction;

    private Long userId;

    private String memo;

    private MultipartFile profile;

    private MultipartFile background;

}
