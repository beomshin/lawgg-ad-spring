package com.was.admin.modules.lawFirmService.service;

import com.was.admin.common.dto.ResponseDto;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmDto;
import com.was.admin.modules.lawFirmService.model.request.EnrollLawFirmRequest;
import com.was.admin.security.model.dto.AdminAdapter;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface LawFirmService {

    ResponseDto findLawFirmList(LawFirmDto lawFirmDto) throws Exception;

    ResponseDto findDetailLawFirm(LawFirmDto lawFirmDto) throws Exception;

    ResponseDto findUserLawFirm(LawFirmDto lawFirmDto) throws Exception;

    @Transactional
    void enrollLawFirm(LawFirmDto lawFirmDto) throws Exception;

    @Transactional
    void updateLawFirm(LawFirmDto lawFirmDto) throws Exception;

    @Transactional
    void updateStatusLawFirm(LawFirmDto lawFirmDto) throws Exception;
    Page findUsersLawFirm(LawFirmDto lawFirmDto) throws Exception;
    Page findApplyUsersLawFirm(LawFirmDto lawFirmDto) throws Exception;
    @Transactional
    boolean confirmLawFirm(LawFirmDto lawFirmDto) throws Exception;
}
