package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.DepthConverter;
import com.was.admin.enums.converters.etc.EmojiParserConverter;
import com.was.admin.enums.converters.status.BoardCommentStatusConverter;
import com.was.admin.enums.element.etc.Depth;
import com.was.admin.enums.element.status.BoardCommentStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "BoardCommentTb")
@Table(
        name = "BoardCommentTb",
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
public class BoardCommentTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardCommentId")
    private Long boardCommentId; // 관리자 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardTb boardTb;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "depth")
    @Convert(converter = DepthConverter.class)
    private Depth depth;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "writer")
    private String writer;

    @Column(name = "ip")
    private String ip;

    @Column(name = "content")
    @Convert(converter = EmojiParserConverter.class)
    private String content;

    @Column(name = "emoticon")
    private String emoticon;

    @Column(name = "report")
    private Long report;

    @Column(name = "status")
    @Convert(converter = BoardCommentStatusConverter.class)
    private BoardCommentStatus status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

    public BoardCommentTb(BoardTb boardTb, Depth depth) {
        this.boardTb = boardTb;
        this.depth = depth;
    }

}
