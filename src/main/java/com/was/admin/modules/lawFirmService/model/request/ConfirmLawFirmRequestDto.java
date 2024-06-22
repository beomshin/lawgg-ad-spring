package com.was.admin.modules.lawFirmService.model.request;

import lombok.*;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmLawFirmRequestDto {
    private Long id;

    private Integer accept; // 0: 미수용, 1: 수용

}
