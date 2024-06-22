package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.enums.converters.etc.EmojiParserConverter;
import com.was.admin.enums.converters.status.BoardStatusConverter;
import com.was.admin.enums.converters.type.LineTypeConverter;
import com.was.admin.enums.converters.type.PostTypeConverter;
import com.was.admin.enums.converters.type.WriterTypeConverter;
import com.was.admin.enums.element.status.BoardStatus;
import com.was.admin.enums.element.type.LineType;
import com.was.admin.enums.element.type.PostType;
import com.was.admin.enums.element.type.WriterType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "MainBoardTb")
@Table(
        name = "MainBoardTb",
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
@DynamicUpdate
public class MainBoardTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mainBoardId")
    private Long mainBoardId; // 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @Column(name = "boardId")
    private Long boardId;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    @Convert(converter = EmojiParserConverter.class)
    private String title;

    @Column(name = "content")
    @Convert(converter = EmojiParserConverter.class)
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "ip")
    private String ip;
    @Column(name = "writeDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일

    @Column(name = "postType")
    @Convert(converter = PostTypeConverter.class)
    private PostType postType;

    @Column(name = "writerType")
    @Convert(converter = WriterTypeConverter.class)
    private WriterType writerType;

    @Column(name = "lineType")
    @Convert(converter = LineTypeConverter.class)
    private LineType lineType;

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "recommendCount")
    private Long recommendCount;

    @Column(name = "view")
    private Long view;

    @Column(name = "report")
    private Long report;

    @Column(name = "status")
    @Convert(converter = BoardStatusConverter.class)
    private BoardStatus status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

    public MainBoardTb(BoardTb boardTb) {
        this.userTb = boardTb.getUserTb();
        this.lawFirmTb = boardTb.getLawFirmTb();
        this.boardId = boardTb.getBoardId();
        this.password = boardTb.getPassword();
        this.title = boardTb.getTitle();
        this.content = boardTb.getContent();
        this.writer = boardTb.getWriter();
        this.ip = boardTb.getIp();
        this.postType = boardTb.getPostType();
        this.writerType = boardTb.getWriterType();
        this.writeDt = boardTb.getWriteDt();
        this.lineType = boardTb.getLineType();
        this.commentCount = boardTb.getCommentCount();
        this.recommendCount = boardTb.getRecommendCount();
        this.view = boardTb.getView();
        this.report = boardTb.getReport();
        this.status = boardTb.getStatus();
    }
}
