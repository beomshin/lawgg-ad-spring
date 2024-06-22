package com.was.admin.modules.lawFirmService.model.response;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmProjection;
import lombok.Data;

@Data
public class FindDetailLawFirmResponse extends ResponseDto {

    LawFirmProjection lawFirm;

    public FindDetailLawFirmResponse(LawFirmProjection lawFirmProjection) {
        this.lawFirm = lawFirmProjection;
    }
}
