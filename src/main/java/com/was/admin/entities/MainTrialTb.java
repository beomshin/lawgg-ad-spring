package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.PrecedentConverter;
import com.was.admin.enums.converters.status.TrialStatusConverter;
import com.was.admin.enums.converters.type.EndingTypeConverter;
import com.was.admin.enums.converters.type.LiveTypeConverter;
import com.was.admin.enums.converters.type.PostTypeConverter;
import com.was.admin.enums.element.etc.Precedent;
import com.was.admin.enums.element.status.TrialStatus;
import com.was.admin.enums.element.type.EndingType;
import com.was.admin.enums.element.type.LiveType;
import com.was.admin.enums.element.type.PostType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "MainTrialTb")
@Table(
        name = "MainTrialTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class MainTrialTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mainTrialId")
    private Long mainTrialId; // 관리자 식별자

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tierId")
    private TierTb tierId;

    @Column(name = "trialId")
    private Long trialId;

    @OneToOne(fetch = FetchType.LAZY)
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

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "precedent")
    @Convert(converter = PrecedentConverter.class)
    private Precedent precedent;

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
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

    public MainTrialTb(TrialTb trialTb) {
        this.userTb = trialTb.getUserTb();
        this.lawFirmTb = trialTb.getLawFirmTb();
        this.tierId = trialTb.getTierId();
        this.trialId = trialTb.getTrialId();
        this.judge = trialTb.getJudge();
        this.id = trialTb.getId();
        this.password = trialTb.getPassword();
        this.title = trialTb.getTitle();
        this.content = trialTb.getContent();
        this.writer = trialTb.getWriter();
        this.writeDt = trialTb.getWriteDt();
        this.subheading = trialTb.getSubheading();
        this.plaintiff = trialTb.getPlaintiff();
        this.plaintiffOpinion = trialTb.getPlaintiffOpinion();
        this.defendant = trialTb.getDefendant();
        this.defendantOpinion = trialTb.getDefendantOpinion();
        this.url = trialTb.getUrl();
        this.playVideo = trialTb.getPlayVideo();
        this.thumbnail = trialTb.getThumbnail();
        this.precedent = trialTb.getPrecedent();
        this.commentCount = trialTb.getCommentCount();
        this.recommendCount = trialTb.getRecommendCount();
        this.view = trialTb.getView();
        this.report = trialTb.getReport();
        this.postType = trialTb.getPostType();
        this.liveType = trialTb.getLiveType();
        this.endingType = trialTb.getEndingType();
        this.status = trialTb.getStatus();
    }

}
