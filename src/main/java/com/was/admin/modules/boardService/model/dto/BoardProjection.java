package com.was.admin.modules.boardService.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.was.admin.enums.converters.status.BoardStatusConverter;
import com.was.admin.enums.element.status.BoardStatus;
import com.was.admin.enums.element.type.LineType;
import com.was.admin.enums.element.type.PostType;
import com.was.admin.enums.element.type.WriterType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.sql.Timestamp;

@Data
public class BoardProjection {

    private Long boardId; // 식별자
    private String title;
    private String content;
    private String writer;
    private String ip;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt; // 수정일
    private Integer postType;
    private Integer writerType;
    private Integer lineType;
    private Long commentCount;
    private Long recommendCount;
    private Long view;
    private Long report;
    private Integer status;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일
    private Long isMain;

    @QueryProjection
    public BoardProjection(
             Long boardId,
             String title,
             String content,
             String writer,
             String ip,
             Timestamp writeDt,
             PostType postType,
             WriterType writerType,
             LineType lineType,
             Long commentCount,
             Long recommendCount,
             Long view,
             Long report,
             BoardStatus status,
             Timestamp regDt,
             Long isMain
    ) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.ip = ip;
        this.writeDt = writeDt;
        this.postType = postType.getCode();
        this.writerType = writerType.getCode();
        this.lineType = lineType.getCode();
        this.commentCount = commentCount;
        this.recommendCount = recommendCount;
        this.view = view;
        this.report = report;
        this.status = status.getCode();
        this.regDt = regDt;
        this.isMain = isMain;
    }
}
