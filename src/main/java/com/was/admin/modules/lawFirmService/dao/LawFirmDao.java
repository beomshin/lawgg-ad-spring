package com.was.admin.modules.lawFirmService.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.was.admin.entities.LawFirmTb;
import com.was.admin.enums.element.status.LawFirmApplyStatus;
import com.was.admin.enums.element.status.LawFirmStatus;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmDto;
import com.was.admin.modules.lawFirmService.model.dto.LawFirmProjection;
import com.was.admin.modules.lawFirmService.model.dto.QLawFirmProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.was.admin.entities.QLawFirmTb.lawFirmTb;
import static com.was.admin.entities.QUserTb.userTb;
import static com.was.admin.entities.QTierTb.tierTb;
import static com.was.admin.entities.QLawFirmApplyTb.lawFirmApplyTb;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LawFirmDao {

    private final JPAQueryFactory jpaQueryFactory;


    public Page<LawFirmProjection> findLawFirmList(LawFirmDto lawFirmDto, Pageable pageable) {
        JPAQuery<LawFirmProjection> content = this.findList(lawFirmDto, pageable); // 게시판 content
        JPAQuery<Long> count = this.findCount(lawFirmDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    public Page<LawFirmProjection> findUserList(LawFirmDto lawFirmDto, Pageable pageable) {
        JPAQuery<LawFirmProjection> content = this.findUserListQuery(lawFirmDto, pageable); // 게시판 content
        JPAQuery<Long> count = this.findUserCount(lawFirmDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    public Page<LawFirmProjection> findUsersLawFirm(LawFirmDto lawFirmDto, Pageable pageable) {
        JPAQuery<LawFirmProjection> content = this.findUsersLawFirmQuery(lawFirmDto, pageable); // 게시판 content
        JPAQuery<Long> count = this.findUsersLawFirmCount(lawFirmDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    public Page<LawFirmProjection> findApplyUsersLawFirm(LawFirmDto lawFirmDto, Pageable pageable) {
        JPAQuery<LawFirmProjection> content = this.findApplyUsersLawFirmQuery(lawFirmDto, pageable); // 게시판 content
        JPAQuery<Long> count = this.findApplyUsersLawFirmCount(lawFirmDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    public LawFirmProjection findDetail(LawFirmDto lawFirmDto) {
        return this.findDetailQuery(lawFirmDto).fetchOne();
    }

    public JPAQuery<LawFirmProjection> findList(LawFirmDto lawFirmDto, Pageable pageable) {
        return jpaQueryFactory.select(new QLawFirmProjection(
                        lawFirmTb.lawFirmId,
                        lawFirmTb.name,
                        lawFirmTb.userTb.nickName,
                        lawFirmTb.profile,
                        lawFirmTb.background,
                        lawFirmTb.status,
                        lawFirmTb.regDt,
                        Expressions.as(this.findUserCount(), "userCnt"),
                        lawFirmTb.memo,
                        lawFirmTb.introduction
                ))
                .from(lawFirmTb)
                .leftJoin(userTb).on(userTb.userId.eq(lawFirmTb.userTb.userId))
                .where(
                        lawFirmTb.name.contains(lawFirmDto.getKeyword())
                                .or(lawFirmTb.userTb.nickName.contains(lawFirmDto.getKeyword())),
                        this.eqType(lawFirmDto.getType())
                )
                .orderBy(lawFirmTb.lawFirmId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    public JPAQuery<Long> findCount(LawFirmDto lawFirmDto) {
        return jpaQueryFactory.select(lawFirmTb.count())
                .where(
                        lawFirmTb.name.contains(lawFirmDto.getKeyword())
                                .or(lawFirmTb.userTb.nickName.contains(lawFirmDto.getKeyword())),
                         this.eqType(lawFirmDto.getType())
                )
                .from(lawFirmTb);
    }

    public JPAQuery<Long> findUserCount() {
        return jpaQueryFactory.select(userTb.count())
                .from(userTb)
                .where(userTb.lawFirmId.lawFirmId.eq(lawFirmTb.lawFirmId));
    }

    public BooleanExpression eqType(Integer type) {
        if (type == 2) {
            return lawFirmTb.status.eq(LawFirmStatus.STOP_STATUS);
        } else if (type ==1){
            return lawFirmTb.status.eq(LawFirmStatus.NORMAL_STATUS);
        } else if (type == 3){
            return lawFirmTb.status.eq(LawFirmStatus.DELETE_STATUS);
        } else {
            return null;
        }
    }

    public JPAQuery<LawFirmProjection> findDetailQuery(LawFirmDto lawFirmDto) {
        return jpaQueryFactory.select(new QLawFirmProjection(
                        lawFirmTb.lawFirmId,
                        lawFirmTb.name,
                        lawFirmTb.userTb.nickName,
                        lawFirmTb.profile,
                        lawFirmTb.background,
                        lawFirmTb.status,
                        lawFirmTb.regDt,
                        Expressions.as(this.findUserCount(), "userCnt"),
                        lawFirmTb.memo,
                        lawFirmTb.introduction
                ))
                .from(lawFirmTb)
                .leftJoin(userTb).on(userTb.userId.eq(lawFirmTb.userTb.userId))
                .where(lawFirmTb.lawFirmId.eq(lawFirmDto.getId()));
    }


    public JPAQuery<LawFirmProjection> findUserListQuery(LawFirmDto lawFirmDto, Pageable pageable) {
        return jpaQueryFactory.select(new QLawFirmProjection(
                        userTb.userId,
                        userTb.nickName,
                        userTb.loginId,
                        userTb.email,
                        userTb.status
                ))
                .from(userTb)
                .where(userTb.nickName.contains(lawFirmDto.getKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    public JPAQuery<Long> findUserCount(LawFirmDto lawFirmDto) {
        return jpaQueryFactory.select(userTb.count())
                .where(
                        userTb.nickName.contains(lawFirmDto.getKeyword())
                )
                .from(userTb);
    }



    public JPAQuery<LawFirmProjection> findUsersLawFirmQuery(LawFirmDto lawFirmDto, Pageable pageable) {
        return jpaQueryFactory.select(new QLawFirmProjection(
                        userTb.tierId.name,
                        userTb.loginId,
                        userTb.nickName,
                        userTb.name,
                        userTb.delFlag,
                        userTb.judgeFlag,
                        userTb.snsType,
                        userTb.status,
                        userTb.regDt
                ))
                .from(userTb)
                .leftJoin(tierTb).on(tierTb.tierId.eq(userTb.userId))
                .where(userTb.lawFirmId.lawFirmId.eq(lawFirmDto.getId()))
                .orderBy(userTb.userId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    public JPAQuery<Long> findUsersLawFirmCount(LawFirmDto lawFirmDto) {
        return jpaQueryFactory.select(userTb.count())
                .from(userTb)
                .where(userTb.lawFirmId.lawFirmId.eq(lawFirmDto.getId()));
    }


    public JPAQuery<LawFirmProjection> findApplyUsersLawFirmQuery(LawFirmDto lawFirmDto, Pageable pageable) {
        return jpaQueryFactory.select(new QLawFirmProjection(
                        lawFirmApplyTb.lawFirmAppyId,
                        lawFirmApplyTb.userTb.nickName,
                        lawFirmApplyTb.title,
                        lawFirmApplyTb.introduction,
                        lawFirmApplyTb.regDt
                ))
                .from(lawFirmApplyTb)
                .leftJoin(userTb).on(userTb.userId.eq(lawFirmApplyTb.userTb.userId))
                .where(lawFirmApplyTb.lawFirmTb.lawFirmId.eq(lawFirmDto.getId()),
                        lawFirmApplyTb.status.eq(LawFirmApplyStatus.APPLY_STATUS))
                .orderBy(lawFirmApplyTb.lawFirmAppyId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    public JPAQuery<Long> findApplyUsersLawFirmCount(LawFirmDto lawFirmDto) {
        return jpaQueryFactory.select(lawFirmApplyTb.count())
                .from(lawFirmApplyTb)
                .where(lawFirmApplyTb.lawFirmTb.lawFirmId.eq(lawFirmDto.getId()),
                        lawFirmApplyTb.status.eq(LawFirmApplyStatus.APPLY_STATUS));
    }

}
