package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.LawFirmDefenseConverter;
import com.was.admin.enums.converters.etc.PrecedentConverter;
import com.was.admin.enums.converters.status.TrialStatusConverter;
import com.was.admin.enums.converters.type.EndingTypeConverter;
import com.was.admin.enums.converters.type.LiveTypeConverter;
import com.was.admin.enums.converters.type.MainPostTypeConverter;
import com.was.admin.enums.converters.type.PostTypeConverter;
import com.was.admin.enums.element.etc.LawFirmDefense;
import com.was.admin.enums.element.etc.Precedent;
import com.was.admin.enums.element.status.TrialStatus;
import com.was.admin.enums.element.type.EndingType;
import com.was.admin.enums.element.type.LiveType;
import com.was.admin.enums.element.type.MainPostType;
import com.was.admin.enums.element.type.PostType;
import com.was.admin.modules.trialService.model.dto.TrialRequestDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TrialTb")
@Table(
        name = "TrialTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString(exclude = {"userTb", "lawFirmTb", "judge", "tierId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class TrialTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trialId")
    private Long trialId; // 관리자 식별자

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tierId")
    private TierTb tierId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "judge")
    private UserTb judge;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "writeDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일

    @Column(name = "subheading")
    private String subheading;

    @Column(name = "plaintiffOpinion")
    private String plaintiffOpinion;

    @Column(name = "defendantOpinion")
    private String defendantOpinion;

    @Column(name = "plaintiff")
    private String plaintiff;

    @Column(name = "defendant")
    private String defendant;

    @Column(name = "url")
    private String url;

    @Column(name = "playVideo")
    private String playVideo;
    
    @Column(name = "altVideoUrl")
    private String altVideoUrl;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "precedent")
    @Convert(converter = PrecedentConverter.class)
    private Precedent precedent;

    @Column(name = "lawFirmDefense")
    @Convert(converter = LawFirmDefenseConverter.class)
    private LawFirmDefense lawFirmDefense;

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "recommendCount")
    private Long recommendCount;

    @Column(name = "view")
    private Integer view;

    @Column(name = "report")
    private Integer report;

    @Column(name = "postType")
    @Convert(converter = PostTypeConverter.class)
    private PostType postType;

    @Column(name = "mainPostType")
    @Convert(converter = MainPostTypeConverter.class)
    private MainPostType mainPostType;
    
    @Column(name = "liveType")
    @Convert(converter = LiveTypeConverter.class)
    private LiveType liveType;

    @Column(name = "endingType")
    @Convert(converter = EndingTypeConverter.class)
    private EndingType endingType;

    @Column(name = "status")
    @Convert(converter = TrialStatusConverter.class)
    private TrialStatus status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

    @Column(name = "liveDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp liveDt; // 수정일

    @Column(name = "endDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp endDt; // 수정일

    @Column(name = "replay")
    private String replay;

    public void updateStatus(TrialRequestDto trialRequestDto) {
        if (this.liveType.equals(LiveType.NON_LIVE_TYPE) && trialRequestDto.getLiveType() == 1) {
            this.liveDt = new Timestamp(System.currentTimeMillis());
        }
        this.liveType = LiveType.of(trialRequestDto.getLiveType());
        if (this.endingType.equals(EndingType.NON_ENDING_TYPE) && trialRequestDto.getEndingType() == 1) {
            this.endDt = new Timestamp(System.currentTimeMillis());
        }
        this.endingType = EndingType.of(trialRequestDto.getEndingType());
        this.status = TrialStatus.of(trialRequestDto.getStatus());
        this.postType = PostType.of(trialRequestDto.getPostType());
        this.mainPostType = MainPostType.of(trialRequestDto.getMainPostType());
        this.altVideoUrl = trialRequestDto.getAltVideoUrl();
        this.precedent = Precedent.of(trialRequestDto.getPrecedent());
        this.lawFirmDefense = LawFirmDefense.of(trialRequestDto.getLawFirmDefense());
    }


    public TrialTb(TrialRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
        this.postType = PostType.of(requestDto.getPostType());
        this.mainPostType = MainPostType.of(requestDto.getMainPostType());
    }

}
