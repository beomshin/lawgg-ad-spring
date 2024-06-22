package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.EmojiParserConverter;
import com.was.admin.enums.converters.flag.DelFlagConverter;
import com.was.admin.enums.converters.flag.ReadFlagConverter;
import com.was.admin.enums.converters.flag.ReplyFlagConverter;
import com.was.admin.enums.element.flag.DelFlag;
import com.was.admin.enums.element.flag.ReadFlag;
import com.was.admin.enums.element.flag.ReplyFlag;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "MessageTb")
@Table(
        name = "MessageTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class MessageTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Long messageId; // 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId")
    private UserTb receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId")
    private UserTb sender;

    @Column(name = "title")
    @Convert(converter = EmojiParserConverter.class)
    private String title;

    @Column(name = "content")
    @Convert(converter = EmojiParserConverter.class)
    private String content;

    @Column(name = "readFlag")
    @Convert(converter = ReadFlagConverter.class)
    private ReadFlag readFlag;

    @Column(name = "receiverDelFlag")
    @Convert(converter = DelFlagConverter.class)
    private DelFlag receiverDelFlag;

    @Column(name = "senderDelFlag")
    @Convert(converter = DelFlagConverter.class)
    private DelFlag senderDelFlag;

    @Column(name = "replyFlag")
    @Convert(converter = ReplyFlagConverter.class)
    private ReplyFlag replyFlag;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

}
