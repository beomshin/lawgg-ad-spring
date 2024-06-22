package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.common.dto.FileDto;
import com.was.admin.enums.converters.status.BoardAttachStatusConverter;
import com.was.admin.enums.element.status.BoardAttachStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "BoardAttachTb")
@Table(
        name = "BoardAttachTb",
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
public class BoardAttachTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardAttachId")
    private Long boardAttachId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardTb boardId;

    @Column(name = "path")
    private String path;

    @Column(name = "oriName")
    private String oriName;

    @Column(name = "newName")
    private String newName;

    @Column(name = "size")
    private Long size;

    @Column(name = "status")
    @Convert(converter = BoardAttachStatusConverter.class)
    private BoardAttachStatus status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

    public BoardAttachTb(BoardTb boardTb, FileDto fileDto) {
        this.boardId = boardTb;
        this.path = fileDto.getPath();
        this.oriName = fileDto.getOriName();
        this.newName = fileDto.getNewName();
        this.size = fileDto.getSize();
        this.status = BoardAttachStatus.NORMAL_STATUS;
    }
}
