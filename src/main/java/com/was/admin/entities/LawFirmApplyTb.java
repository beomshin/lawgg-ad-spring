package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.LawFirmAcceptConverter;
import com.was.admin.enums.converters.status.LawFirmApplyStatusConverter;
import com.was.admin.enums.element.etc.LawFirmAccept;
import com.was.admin.enums.element.status.LawFirmApplyStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "LawFirmApplyTb")
@Table(
        name = "LawFirmApplyTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class LawFirmApplyTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lawFirmAppyId")
    private Long lawFirmAppyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserTb userTb;
    @Column(name = "title")
    private String title;
    @Column(name = "introduction")
    private String introduction;

    @Convert(converter = LawFirmApplyStatusConverter.class)
    @Column(name = "status")
    private LawFirmApplyStatus status;

    @Convert(converter = LawFirmAcceptConverter.class)
    @Column(name = "accept")
    private LawFirmAccept accept;

    @Column(name = "confirmDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp confirmDt; // 확정일

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일


    public void confirm() {
        this.accept = LawFirmAccept.NON_ACCEPT;
        this.status = LawFirmApplyStatus.END_STATUS; // 신청 처리 완료
        this.confirmDt = new Timestamp(System.currentTimeMillis()); // 확인일
    }

    public void accept() {
        this.accept = LawFirmAccept.ACCEPT;
    }

    public void refuse() {
        this.accept = LawFirmAccept.NON_ACCEPT;
    }

}
