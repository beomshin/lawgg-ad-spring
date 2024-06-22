package com.was.admin.modules.lawFirmService.service.impl;

import com.was.admin.common.dto.FileDto;
import com.was.admin.common.dto.GlobalCode;
import com.was.admin.common.dto.ResponseDto;
import com.was.admin.common.file.FileService;
import com.was.admin.entities.LawFirmApplyTb;
import com.was.admin.entities.LawFirmTb;
import com.was.admin.entities.UserTb;
import com.was.admin.enums.element.etc.LawFirmAccept;
import com.was.admin.enums.element.status.LawFirmApplyStatus;
import com.was.admin.exception.LgException;
import com.was.admin.modules.lawFirmService.dao.LawFirmDao;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmDto;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmProjection;
import com.was.admin.modules.lawFirmService.model.request.EnrollLawFirmRequest;
import com.was.admin.modules.lawFirmService.model.response.FindDetailLawFirmResponse;
import com.was.admin.modules.lawFirmService.model.response.FindLawFirmListResponse;
import com.was.admin.modules.lawFirmService.model.response.FindUserLawFirmResponse;
import com.was.admin.modules.lawFirmService.service.LawFirmService;
import com.was.admin.repositories.LawFirmApplyTbRepository;
import com.was.admin.repositories.LawFirmTbRepository;
import com.was.admin.repositories.UserTbRepository;
import com.was.admin.security.model.dto.AdminAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmServiceImpl implements LawFirmService {

    private final LawFirmTbRepository lawFirmTbRepository;
    private final UserTbRepository userTbRepository;
    private final FileService<FileDto> fileService;
    private final LawFirmDao lawFirmDao;

    private final LawFirmApplyTbRepository lawFirmApplyTbRepository;

    @Override
    public ResponseDto findLawFirmList(LawFirmDto lawFirmDto) throws Exception {
        Page<LawFirmProjection> list = lawFirmDao.findLawFirmList(lawFirmDto, PageRequest.of(lawFirmDto.getPage(), lawFirmDto.getPageNum()));
        return new FindLawFirmListResponse(list);
    }

    @Override
    public ResponseDto findDetailLawFirm(LawFirmDto lawFirmDto) throws Exception {
        LawFirmProjection lawFirmProjection = lawFirmDao.findDetail(lawFirmDto);
        return new FindDetailLawFirmResponse(lawFirmProjection);
    }

    @Override
    public ResponseDto findUserLawFirm(LawFirmDto lawFirmDto) throws Exception {
        Page<LawFirmProjection> list = lawFirmDao.findUserList(lawFirmDto, PageRequest.of(lawFirmDto.getPage(), lawFirmDto.getPageNum()));
        return new FindUserLawFirmResponse(list);
    }

    @Override
    public void enrollLawFirm(LawFirmDto lawFirmDto) throws Exception {
        if (lawFirmTbRepository.countByName(lawFirmDto.getTitle()) > 0) throw new LgException(GlobalCode.OVER_LAP_LAW_FIRM_NAME); // 중복 로펌명
        String profile = this.getImageUrl(lawFirmDto.getProfile()); // 프로필 이미지
        String background = this.getImageUrl(lawFirmDto.getBackground()); // 배경 이미지
        LawFirmTb lawFirmTb = lawFirmTbRepository.save(new LawFirmTb(lawFirmDto, profile, background));
        userTbRepository.updateLawFirm(lawFirmDto.getUserId(), lawFirmTb, new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void updateLawFirm(LawFirmDto lawFirmDto) throws Exception {
        LawFirmTb lawFirmTb = lawFirmTbRepository.findById(lawFirmDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        String profile = this.getImageUrl(lawFirmDto.getProfile()); // 프로필 이미지
        String background = this.getImageUrl(lawFirmDto.getBackground()); // 배경
        lawFirmTb.update(lawFirmDto, profile, background);
    }

    @Override
    public void updateStatusLawFirm(LawFirmDto lawFirmDto) throws Exception {
        LawFirmTb lawFirmTb = lawFirmTbRepository.findById(lawFirmDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        lawFirmTb.updateStatus(lawFirmDto.getStatus());
    }

    @Override
    public Page findUsersLawFirm(LawFirmDto lawFirmDto) throws Exception {
        return lawFirmDao.findUsersLawFirm(lawFirmDto, PageRequest.of(lawFirmDto.getPage(), lawFirmDto.getPageNum()));
    }

    @Override
    public Page findApplyUsersLawFirm(LawFirmDto lawFirmDto) throws Exception {
        return lawFirmDao.findApplyUsersLawFirm(lawFirmDto, PageRequest.of(lawFirmDto.getPage(), lawFirmDto.getPageNum()));
    }

    @Override
    public boolean confirmLawFirm(LawFirmDto lawFirmDto) throws Exception {
        LawFirmApplyTb lawFirmApplyTb = lawFirmApplyTbRepository.findById(lawFirmDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_APPLY_INFO));

        if (lawFirmApplyTb.getStatus().equals(LawFirmApplyStatus.END_STATUS)) throw new LgException(GlobalCode.ALREADY_END_APPLY); // 종료된 지원서
        UserTb userTb =lawFirmApplyTb.getUserTb();
        lawFirmApplyTb.confirm();

        if (userTb.getLawFirmId() != null) { // 로펌을 이미 가입한 경우
            return false;
        }else if (LawFirmAccept.ACCEPT.getCode() == lawFirmDto.getAccept()) { // 로펌 가입 승인
            userTb.enrollLawFirm(lawFirmApplyTb.getLawFirmTb());
            lawFirmApplyTb.accept();
        } else if (LawFirmAccept.NON_ACCEPT.getCode() == lawFirmDto.getAccept()){ // 로펌 가입 거절
            lawFirmApplyTb.refuse();
        }

        return true;
    }


    public String getImageUrl(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        return fileService.uploadSingle(file).getPath();
    }
}
