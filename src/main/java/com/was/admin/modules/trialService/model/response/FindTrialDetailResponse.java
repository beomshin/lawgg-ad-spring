package com.was.admin.modules.trialService.model.response;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.common.dto.FileResponse;
import com.was.admin.modules.trialService.model.dto.TrialProjectionDto;
import lombok.Data;

import java.util.List;

@Data
public class FindTrialDetailResponse extends ResponseDto {


    private TrialProjectionDto trial;
    private List<FileResponse> files;
    public FindTrialDetailResponse(TrialProjectionDto trial, List<FileResponse> attachTbs) {
        this.trial = trial;
        this.files = attachTbs;
    }
}
