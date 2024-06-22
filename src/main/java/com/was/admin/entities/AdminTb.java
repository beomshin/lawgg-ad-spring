
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
import java.sql.Timestamp;

@Entity(name = "AdminTb")
@Table(
        name = "AdminTb",
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
public class AdminTb { // 관리자 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    private Long adminId; // 식별자

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "regDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

}
