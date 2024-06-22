package com.was.admin.modules.trialService.dao;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.was.admin.entities.TrialTb;
import com.was.admin.enums.element.status.TrialStatus;
import com.was.admin.enums.element.type.EndingType;
import com.was.admin.enums.element.type.LiveType;
import com.was.admin.modules.trialService.model.dto.QTrialProjectionDto;
import com.was.admin.modules.trialService.model.dto.TrialProjectionDto;
import com.was.admin.modules.trialService.model.dto.TrialRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.was.admin.entities.QBoardTb.boardTb;
import static com.was.admin.entities.QMainBoardTb.mainBoardTb;
import static com.was.admin.entities.QTrialTb.trialTb;
import static com.was.admin.entities.QMainTrialTb.mainTrialTb;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TrialDao {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<TrialProjectionDto> findListTrial(TrialRequestDto trialRequestDto, Pageable pageable) {
        JPAQuery<TrialProjectionDto> content = this.findAllListTrial(trialRequestDto, pageable); // 게시판 content
        JPAQuery<Long> count = this.findAllListTrialCount(trialRequestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    private JPAQuery<TrialProjectionDto> findAllListTrial(TrialRequestDto requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QTrialProjectionDto(
                    trialTb.trialId,
                    trialTb.title,
                    trialTb.content,
                    trialTb.writer,
                    trialTb.writeDt,
                    trialTb.precedent,
                    trialTb.lawFirmDefense,
                    trialTb.commentCount,
                    trialTb.recommendCount,
                    trialTb.view,
                    trialTb.report,
                    trialTb.postType,
                    trialTb.mainPostType,
                    trialTb.liveType,
                    trialTb.endingType,
                    trialTb.liveDt,
                    trialTb.endDt,
                    trialTb.status,
                    this.isMain()
                ))
                .from(trialTb)
                .where(
                        this.eqLive(requestDto.getLiveType()),
                        this.eqEnd(requestDto.getEndType()),
                        this.eqStatus(requestDto.getStatusType()),
                        trialTb.writer.contains(requestDto.getKeyword()).or(
                                trialTb.title.contains(requestDto.getKeyword())
                        )
                )
                .orderBy(trialTb.trialId.desc(), this.getOrder(requestDto.getType())) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(trialTb.trialId);
    }

    private JPAQuery<Long> findAllListTrialCount(TrialRequestDto requestDto) {
        return jpaQueryFactory
                .select(trialTb.count())
                .where(
                        this.eqLive(requestDto.getLiveType()),
                        this.eqEnd(requestDto.getEndType()),
                        this.eqStatus(requestDto.getStatusType()),
                        trialTb.writer.contains(requestDto.getKeyword()).or(
                                trialTb.title.contains(requestDto.getKeyword())
                        )
                )
                .from(trialTb);

    }

    public JPAQuery<Long> isMain() {
        return jpaQueryFactory.select(mainTrialTb.count()).from(mainTrialTb).where(mainTrialTb.trialId.eq(trialTb.trialId));
    }

    private BooleanExpression eqLive(Integer type) {
        switch (type) {
            case 1: return trialTb.liveType.eq(LiveType.NON_LIVE_TYPE);
            case 2: return trialTb.liveType.eq(LiveType.LIVE_TYPE);
            default: return null;
        }
    }

    private BooleanExpression eqEnd(Integer type) {
        switch (type) {
            case 1: return trialTb.endingType.eq(EndingType.NON_ENDING_TYPE);
            case 2: return trialTb.endingType.eq(EndingType.ENDING_TYPE);
            default: return null;
        }
    }

    private BooleanExpression eqStatus(Integer type) {
        switch (type) {
            case 1: return trialTb.status.eq(TrialStatus.NORMAL_STATUS);
            case 2: return trialTb.status.eq(TrialStatus.DELETE_STATUS);
            case 3: return trialTb.status.eq(TrialStatus.REPORT_STATUS);
            default: return null;
        }
    }

    public OrderSpecifier getOrder(Integer type) {
        switch (type) {
            case 0: return trialTb.commentCount.desc();
            case 1: return trialTb.recommendCount.desc();
            case 2: return trialTb.view.desc();
            case 3: return trialTb.report.desc();
        }
        return null;
    }
}
