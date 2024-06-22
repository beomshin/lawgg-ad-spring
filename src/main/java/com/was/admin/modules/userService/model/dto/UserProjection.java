package com.was.admin.modules.userService.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.was.admin.entities.LawFirmTb;
import com.was.admin.entities.TierTb;
import com.was.admin.enums.converters.etc.AESCryptConverter;
import com.was.admin.enums.converters.flag.AuthFlagConverter;
import com.was.admin.enums.converters.flag.DelFlagConverter;
import com.was.admin.enums.converters.flag.JudgeFlagConverter;
import com.was.admin.enums.converters.status.UserStatusConverter;
import com.was.admin.enums.converters.type.SnsTypeConverter;
import com.was.admin.enums.element.flag.AuthFlag;
import com.was.admin.enums.element.flag.DelFlag;
import com.was.admin.enums.element.flag.JudgeFlag;
import com.was.admin.enums.element.status.UserStatus;
import com.was.admin.enums.element.type.SnsType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProjection {

    private Long userId; // 식별자
    private String tier;
    private String loginId;
    private String nickName;
    private String name;
    private Long boardCount;
    private Long trialCount;
    private Long commentCount;
    private Integer snsType;
    private Integer personalPeriod;
    private Integer delFlag;
    private Integer authFlag;
    private Integer judgeFlag;
    private Integer status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일


    @QueryProjection
    public UserProjection(
            Long userId,
             String tier,
             String loginId,
             String nickName,
             String name,
             Long boardCount,
             Long trialCount,
             Long commentCount,
             SnsType snsType,
             Integer personalPeriod,
             DelFlag delFlag,
             AuthFlag authFlag,
             JudgeFlag judgeFlag,
             UserStatus status,
             Timestamp regDt
    ) {
        this.userId =userId;
        this.tier = tier;
        this.loginId = loginId;
        this.nickName = nickName;
        this.name = name;
        this.boardCount = boardCount;
        this.trialCount = trialCount;
        this.commentCount = commentCount;
        this.snsType = snsType.getCode();
        this.personalPeriod = personalPeriod;
        this.delFlag = delFlag.getCode();
        this.authFlag = authFlag.getCode();
        this.judgeFlag = judgeFlag.getCode();
        this.status = status.getCode();
        this.regDt = regDt;
    }


}
