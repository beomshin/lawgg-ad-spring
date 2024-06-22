package com.was.admin.modules.trialService.service;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.exception.LgException;
import com.was.admin.modules.trialService.model.dto.TrialRequestDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialService {

    ResponseDto findTrialList(TrialRequestDto trialRequestDto) throws LgException;
    @Transactional
    void updateTrialMain(TrialRequestDto trialRequestDto) throws LgException;
    ResponseDto findTrialDetail(TrialRequestDto trialRequestDto) throws LgException;
    @Transactional
    void updateTrialStatus(TrialRequestDto trialRequestDto) throws LgException;
    @Transactional
    void enrollNoticeTrial(TrialRequestDto requestDto) throws Exception;
	void updateTrialHot(TrialRequestDto trialRequestDto) throws Exception;
}
