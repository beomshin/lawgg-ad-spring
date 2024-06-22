package com.was.admin.modules.trialService.service.impl;

import com.was.admin.common.dto.FileDto;
import com.was.admin.common.dto.GlobalCode;
import com.was.admin.common.dto.ResponseDto;
import com.was.admin.common.file.FileService;
import com.was.admin.entities.MainTrialTb;
import com.was.admin.entities.TrialAttachTb;
import com.was.admin.entities.TrialCommentTb;
import com.was.admin.entities.TrialTb;
import com.was.admin.enums.element.etc.Depth;
import com.was.admin.enums.element.type.MainPostType;
import com.was.admin.exception.LgException;
import com.was.admin.common.dto.FileResponse;
import com.was.admin.modules.trialService.dao.TrialDao;
import com.was.admin.modules.trialService.model.dto.TrialProjectionDto;
import com.was.admin.modules.trialService.model.dto.TrialRequestDto;
import com.was.admin.modules.trialService.model.response.FindTrialDetailResponse;
import com.was.admin.modules.trialService.model.response.FindTrialListResponse;
import com.was.admin.modules.trialService.service.TrialService;
import com.was.admin.repositories.MainTrialTbRepository;
import com.was.admin.repositories.TrialAttachTbRepository;
import com.was.admin.repositories.TrialCommentTbRepository;
import com.was.admin.repositories.TrialTbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final TrialTbRepository trialTbRepository;
    private final MainTrialTbRepository mainTrialTbRepository;
    private final TrialAttachTbRepository trialAttachTbRepository;
    private final TrialCommentTbRepository trialCommentTbRepository;
    private final FileService<FileDto> fileService;

    @Override
    public ResponseDto findTrialList(TrialRequestDto trialRequestDto) throws LgException {
        Page<TrialProjectionDto> page = trialDao.findListTrial(trialRequestDto, PageRequest.of(trialRequestDto.getPage(), trialRequestDto.getPageNum()));
        return new FindTrialListResponse(page);
    }

    @Override
    public void updateTrialMain(TrialRequestDto trialRequestDto) throws LgException {
        TrialTb trialTb = trialTbRepository.findById(trialRequestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_TRIAL));
        if (trialRequestDto.getType() == 0) {
            mainTrialTbRepository.save(new MainTrialTb(trialTb));
        } else {
            MainTrialTb mainTrialTb = mainTrialTbRepository.findByTrialId(trialTb.getTrialId());
            mainTrialTbRepository.deleteById(mainTrialTb.getMainTrialId());
        }
    }

    @Override
    public ResponseDto findTrialDetail(TrialRequestDto trialRequestDto) throws LgException {
        TrialTb trialTb = trialTbRepository.findById(trialRequestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_TRIAL));
        TrialProjectionDto trialProjectionDto = new TrialProjectionDto(trialTb);
        List<FileResponse> attachTbs = trialAttachTbRepository.findByTrialId_TrialId(trialTb.getTrialId()).stream().map(FileResponse::new).collect(Collectors.toList());
        return new FindTrialDetailResponse(trialProjectionDto, attachTbs);
    }
    
	@Override
    public void updateTrialStatus(TrialRequestDto trialRequestDto) throws LgException {
        if(trialRequestDto.getMainPostType() == 1) {
        	trialTbRepository.updateTrialUnHotAll(trialRequestDto.getId());
        }
        TrialTb trialTb = trialTbRepository.findById(trialRequestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_TRIAL));
        trialTb.updateStatus(trialRequestDto);
    }

    @Override
    public void enrollNoticeTrial(TrialRequestDto requestDto) throws Exception {
        TrialTb trialTb = new TrialTb(requestDto);
 
        trialTbRepository.save(trialTb);
        trialCommentTbRepository.save(new TrialCommentTb(trialTb, Depth.ROOT_COMMENT));
        this.addFiles(requestDto.getFiles(), trialTb);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addFiles(List<MultipartFile> files, TrialTb trialTb) {
        if (files == null || files.size() ==0 ) return;
        List<TrialAttachTb> attachTbs = fileService.uploadMultiple(files).stream().filter(it -> it != null)
                .map(it -> new TrialAttachTb(trialTb, it)).collect(Collectors.toList());
        trialAttachTbRepository.saveAll(attachTbs);
    }
    @Transactional(readOnly = false)
    public void updateTrialHot(TrialRequestDto requestDto) throws Exception {
    	if(requestDto.getMainPostType() >0) {
    		requestDto.setMainPostType(0);
    		trialTbRepository.updateTrialUnHot(requestDto.getId());
    	} else {
    		requestDto.setMainPostType(1);
    		trialTbRepository.updateTrialUnHotAll(requestDto.getId());
    		trialTbRepository.updateTrialHot(requestDto.getId());
    	}
    }
    
    
}
