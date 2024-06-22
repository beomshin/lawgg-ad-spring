package com.was.admin.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.entities.BoardAttachTb;
import com.was.admin.entities.TrialAttachTb;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class FileResponse {

    private Long boardAttachId;
    private Long trialdAttachId;
    private String path;
    private String oriName;
    private String newName;
    private Long size;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일
    public FileResponse(BoardAttachTb boardAttachTb) {
        this.boardAttachId = boardAttachTb.getBoardAttachId();
        this.path = boardAttachTb.getPath();
        this.oriName = boardAttachTb.getOriName();
        this.newName = boardAttachTb.getNewName();
        this.size = boardAttachTb.getSize();
        this.regDt = boardAttachTb.getRegDt();
    }

    public FileResponse(TrialAttachTb trialAttachTb) {
        this.trialdAttachId = trialAttachTb.getTrialAttachId();
        this.path = trialAttachTb.getPath();
        this.oriName = trialAttachTb.getOriName();
        this.newName = trialAttachTb.getNewName();
        this.size = trialAttachTb.getSize();
        this.regDt = trialAttachTb.getRegDt();
    }
}
