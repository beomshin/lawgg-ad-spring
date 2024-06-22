package com.was.admin.modules.trialService.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.was.admin.entities.TrialTb;
import com.was.admin.enums.converters.etc.LawFirmDefenseConverter;
import com.was.admin.enums.converters.etc.PrecedentConverter;
import com.was.admin.enums.converters.status.TrialStatusConverter;
import com.was.admin.enums.converters.type.EndingTypeConverter;
import com.was.admin.enums.converters.type.LiveTypeConverter;
import com.was.admin.enums.converters.type.PostTypeConverter;
import com.was.admin.enums.element.etc.LawFirmDefense;
import com.was.admin.enums.element.etc.Precedent;
import com.was.admin.enums.element.status.TrialStatus;
import com.was.admin.enums.element.type.EndingType;
import com.was.admin.enums.element.type.LiveType;
import com.was.admin.enums.element.type.MainPostType;
import com.was.admin.enums.element.type.PostType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.sql.Timestamp;

@Data
@ToString
public class TrialProjectionDto {

    private Long trialId;
    private Integer status;
    private String id;
    private String title;
    private String content;
    private String writer;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt; //
    private Integer precedent;
    private Integer lawFirmDefense;
    private Long commentCount;
    private Long recommendCount;
    private Integer view;
    private Integer report;
    private Integer postType;
    private Integer mainPostType;
    private Integer liveType;
    private Integer endingType;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp liveDt; //
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp endDt; // 수정일
    private String replay;
    private Long isMain;
    private String subheading;
    private String plaintiffOpinion;
    private String defendantOpinion;
    private String plaintiff;
    private String defendant;
    private String url;
    private String playVideo;
    private String altVideoUrl;
    private String thumbnail;
    private String judgeName;
    private String tireName;
    private String lawFirmName;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 수정일
    @QueryProjection
    public TrialProjectionDto(
            Long trialId,
            String title,
            String content,
            String writer,
            Timestamp writeDt,
            Precedent precedent,
            LawFirmDefense lawFirmDefense,
            Long commentCount,
            Long recommendCount,
            Integer view,
            Integer report,
            PostType postType,
            MainPostType mainPostType,
            LiveType liveType,
            EndingType endingType,
            Timestamp liveDt,
            Timestamp endDt,
            TrialStatus status,
            Long isMain
    ) {
        this.trialId = trialId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.writeDt = writeDt;
        this.precedent = precedent.getCode();
        this.lawFirmDefense = lawFirmDefense.getCode();
        this.commentCount = commentCount;
        this.recommendCount = recommendCount;
        this.view = view;
        this.report = report;
        this.postType = postType.getCode();
        this.mainPostType = mainPostType.getCode();
        this.liveType = liveType.getCode();
        this.endingType = endingType.getCode();
        this.status = status.getCode();
        this.liveDt = liveDt;
        this.endDt = endDt;
        this.isMain = isMain;
    }


    public TrialProjectionDto(TrialTb trialTb) {
        this.trialId = trialTb.getTrialId();
        this.title = trialTb.getTitle();
        this.content = trialTb.getContent();
        this.writer = trialTb.getWriter();
        this.writeDt = trialTb.getWriteDt();
        this.precedent = trialTb.getPrecedent().getCode();
        this.lawFirmDefense = trialTb.getLawFirmDefense().getCode();
        this.commentCount = trialTb.getCommentCount();
        this.recommendCount = trialTb.getRecommendCount();
        this.view = trialTb.getView();
        this.report = trialTb.getReport();
        this.postType = trialTb.getPostType().getCode();
        this.mainPostType = trialTb.getMainPostType().getCode();
        this.liveType = trialTb.getLiveType().getCode();
        this.endingType = trialTb.getEndingType().getCode();
        this.status = trialTb.getStatus().getCode();
        this.liveDt = trialTb.getLiveDt();
        this.endDt = trialTb.getEndDt();
        this.replay = trialTb.getReplay();
        this.subheading=trialTb.getSubheading();
        this.id = trialTb.getId();
        this.plaintiff = trialTb.getPlaintiff();
        this.plaintiffOpinion = trialTb.getPlaintiffOpinion();
        this.defendant = trialTb.getDefendant();
        this.defendantOpinion = trialTb.getDefendantOpinion();
        this.url = trialTb.getUrl();
        this.playVideo = trialTb.getPlayVideo();
        this.altVideoUrl = trialTb.getAltVideoUrl();
        this.thumbnail = trialTb.getThumbnail();
        this.regDt = trialTb.getRegDt();
        if (trialTb.getTierId() != null) {
            this.tireName = trialTb.getTierId().getName();
        }
        if (trialTb.getJudge() != null) {
            this.judgeName = trialTb.getJudge().getName();
        }
        if (trialTb.getLawFirmTb() != null) {
            this.lawFirmName = trialTb.getLawFirmTb().getName();
        }
    }
}
