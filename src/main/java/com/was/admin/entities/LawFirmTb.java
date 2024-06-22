package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.EmojiParserConverter;
import com.was.admin.enums.element.status.LawFirmStatus;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmDto;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "LawFirmTb")
@Table(
        name = "LawFirmTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString(exclude = {"userTb"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class LawFirmTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lawFirmId")
    private Long lawFirmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @Column(name = "name")
    private String name;

    @Column(name = "profile")
    private String profile;

    @Column(name = "background")
    private String background;

    @Column(name = "memo")
    private String memo;

    @Column(name = "introduction")
    @Convert(converter = EmojiParserConverter.class)
    private String introduction;

    @Column(name = "status")
    private LawFirmStatus status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

    public LawFirmTb(LawFirmDto lawFirmDto, String profile, String background) {
        this.userTb = new UserTb(lawFirmDto.getUserId());
        this.name = lawFirmDto.getTitle();
        this.introduction = lawFirmDto.getIntroduction();
        this.profile = profile;
        this.background = background;
        this.memo = lawFirmDto.getMemo();
    }

    public void update(LawFirmDto lawFirmDto, String profile, String background) {
        if (lawFirmDto.getUserId() != null) {
            this.userTb = new UserTb(lawFirmDto.getUserId());
        }
        this.name = lawFirmDto.getTitle();
        this.introduction = lawFirmDto.getIntroduction();
        if (StringUtils.isNotBlank(profile)) {
            this.profile = profile;
        }
        if (StringUtils.isNotBlank(background)) {
            this.background = background;
        }
        this.status = LawFirmStatus.of(lawFirmDto.getStatus());
        this.memo = lawFirmDto.getMemo();
    }

    public void updateStatus(Integer status) {
        this.status = LawFirmStatus.of(status);
    }

}
