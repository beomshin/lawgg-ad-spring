package com.was.admin.modules.lawFirmService.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.was.admin.enums.element.flag.DelFlag;
import com.was.admin.enums.element.flag.JudgeFlag;
import com.was.admin.enums.element.status.LawFirmStatus;
import com.was.admin.enums.element.status.UserStatus;
import com.was.admin.enums.element.type.SnsType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LawFirmProjection {

    private Long id;
    private Long lawFirmId;
    private String name;
    private String repName;
    private String profile;
    private String background;
    private Integer status;
    private Long userCnt;
    private String memo;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt;

    private Long userId;
    private String nickName;
    private String loginId;
    private String email;
    private String introduction;

    private String tier;
    private Integer delFlag;
    private Integer judgeFlag;
    private Integer snsType;
    private String title;

    @QueryProjection
    public LawFirmProjection(
            Long lawFirmId,
            String name,
            String repName,
            String profile,
            String background,
            LawFirmStatus status,
            Timestamp regDt,
            Long userCnt,
            String memo,
            String introduction
    ) {
        this.lawFirmId = lawFirmId;
        this.name = name;
        this.repName = repName;
        this.profile = profile;
        this.background = background;
        this.status = status.getCode();
        this.regDt = regDt;
        this.userCnt = userCnt;
        this.memo = memo;
        this.introduction = introduction;
    }

    @QueryProjection
    public LawFirmProjection(
             Long userId,
            String nickName,
            String loginId,
            String email,
             UserStatus status
    ) {
        this.userId = userId;
        this.nickName = nickName;
        this.loginId = loginId;
        this.email = email;
        this.status = status.getCode();
    }

    @QueryProjection
    public LawFirmProjection(
            String tier,
            String loginId,
            String nickName,
            String name,
            DelFlag delFlag,
            JudgeFlag judgeFlag,
            SnsType snsType,
            UserStatus status,
            Timestamp regDt
    ) {
        this.tier = tier;
        this.loginId = loginId;
        this.nickName = nickName;
        this.name = name;
        this.delFlag = delFlag.getCode();
        this.judgeFlag = judgeFlag.getCode();
        this.snsType = snsType.getCode();
        this.status = status.getCode();
        this.regDt = regDt;
    }

    @QueryProjection
    public LawFirmProjection(
            Long id,
            String name,
            String title,
            String introduction,
            Timestamp regDt
    ) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.introduction = introduction;
        this.regDt = regDt;
    }

}
