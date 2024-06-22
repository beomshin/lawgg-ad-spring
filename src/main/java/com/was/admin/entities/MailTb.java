package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.AESCryptConverter;
import com.was.admin.enums.converters.etc.VerificationConverter;
import com.was.admin.enums.element.etc.Verification;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity(name = "MailTb")
@Table(
        name = "MailTb",
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
public class MailTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailId")
    private Long mailId;

    @Column(name = "txId")
    private String txId;

    @Column(name = "receiver")
    @Convert(converter = AESCryptConverter.class)
    private String receiver;

    @Column(name = "code")
    private String code;

    @Column(name = "verification")
    @Convert(converter = VerificationConverter.class)
    private Verification verification;

    @Column(name = "expired")
    private Date expired;

    @Column(name = "regDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

}
