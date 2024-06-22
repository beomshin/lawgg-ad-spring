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
import com.was.admin.modules.boardService.model.dto.BoardRequestDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "BoardTb")
@Table(
        name = "BoardTb",
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
public class BoardTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long boardId; // 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

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

    public void updateStatus(Integer status, Integer type) {
        this.status = BoardStatus.of(status);
        this.postType = PostType.of(type);
    }

    public BoardTb(BoardRequestDto boardRequestDto) {
        this.postType = PostType.of(boardRequestDto.getType());
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.writer = boardRequestDto.getWriter();
        this.writerType = WriterType.ANONYMOUS_TYPE;
        this.lineType = LineType.ALL;
    }
}
