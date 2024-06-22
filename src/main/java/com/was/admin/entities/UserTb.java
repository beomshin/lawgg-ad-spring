package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "UserTb")
@Table(
        name = "UserTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"loginId"}
                )
        }
)
@Getter
@ToString(exclude = {"tierId", "lawFirmId"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class UserTb { // 관리자 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId; // 식별자

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tierId")
    private TierTb tierId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmId;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "snsId")
    private String snsId;

    @Column(name = "ci")
    private String ci;

    @Column(name = "password")
    private String password;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @Convert(converter = AESCryptConverter.class)
    private String email;

    @Column(name = "hashEmail")
    private String hashEmail;

    @Column(name = "phone")
    @Convert(converter = AESCryptConverter.class)
    private String phone;

    @Column(name = "profile")
    private String profile;

    @Column(name = "boardCount")
    private Long boardCount;

    @Column(name = "trialCount")
    private Long trialCount;

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "snsType")
    @Convert(converter = SnsTypeConverter.class)
    private SnsType snsType;

    @Column(name = "personalPeriod")
    private Integer personalPeriod;

    @Column(name = "delFlag")
    @Convert(converter = DelFlagConverter.class)
    private DelFlag delFlag;

    @Column(name = "authFlag")
    @Convert(converter = AuthFlagConverter.class)
    private AuthFlag authFlag;

    @Column(name = "judgeFlag")
    @Convert(converter = JudgeFlagConverter.class)
    private JudgeFlag judgeFlag;

    @Column(name = "status")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

    @Column(name = "lawFirmEnrollDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp lawFirmEnrollDt; // 등록일

    @Column(name = "regDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

    public UserTb(Long userId) {
        this.userId = userId;
    }

    public void enrollLawFirm(LawFirmTb lawFirmTb) {
        this.lawFirmId = lawFirmTb;
        this.lawFirmEnrollDt = new Timestamp(System.currentTimeMillis());
    }
}
