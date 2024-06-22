package com.was.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.was.admin.common.dto.FileDto;
import com.was.admin.enums.converters.status.TrialAttachStatusConverter;
import com.was.admin.enums.element.status.TrialAttachStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TrialAttachTb")
@Table(
        name = "TrialAttachTb",
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
public class TrialAttachTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trialAttachId")
    private Long trialAttachId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trialId")
    private TrialTb trialId;

    @Column(name = "path")
    private String path;

    @Column(name = "oriName")
    private String oriName;

    @Column(name = "newName")
    private String newName;

    @Column(name = "size")
    private Long size;

    @Column(name = "status")
    @Convert(converter = TrialAttachStatusConverter.class)
    private TrialAttachStatus status;


    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

    public TrialAttachTb(TrialTb trialTb, FileDto fileDto) {
        this.trialId = trialTb;
        this.path = fileDto.getPath();
        this.oriName = fileDto.getOriName();
        this.newName = fileDto.getNewName();
        this.size = fileDto.getSize();
    }

}
