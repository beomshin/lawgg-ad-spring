package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.DepthConverter;
import com.was.admin.enums.converters.status.TrialCommentStatusConverter;
import com.was.admin.enums.element.etc.Depth;
import com.was.admin.enums.element.status.TrialCommentStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TrialCommentTb")
@Table(
        name = "TrialCommentTb",
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
public class TrialCommentTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trialCommentId")
    private Long trialCommentId; // 관리자 식별자

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trialId")
    private TrialTb trialTb;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "depth")
    @Convert(converter = DepthConverter.class)
    private Depth depth;

    @Column(name = "ip")
    private String ip;
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "writer")
    private String writer;

    @Column(name = "content")
    private String content;

    @Column(name = "emoticon")
    private String emoticon;

    @Column(name = "report")
    private Long report;

    @Column(name = "status")
    @Convert(converter = TrialCommentStatusConverter.class)
    private TrialCommentStatus status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일


    public TrialCommentTb(TrialTb trialTb, Depth root) {
        this.trialTb = trialTb;
        this.depth = root;
    }
}
