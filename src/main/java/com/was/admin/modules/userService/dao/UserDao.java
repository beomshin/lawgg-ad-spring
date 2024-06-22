package com.was.admin.modules.userService.dao;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.was.admin.enums.element.status.UserStatus;
import com.was.admin.enums.element.type.SnsType;
import com.was.admin.modules.userService.model.dto.QUserProjection;
import com.was.admin.modules.userService.model.dto.UserDto;
import com.was.admin.modules.userService.model.dto.UserProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.was.admin.entities.QUserTb.userTb;
import static com.was.admin.entities.QTierTb.tierTb;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDao {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<UserProjection> findUsers(UserDto userDto, Pageable pageable) {
        JPAQuery<UserProjection> content = this.findUsersQuery(userDto, pageable); // 게시판 content
        JPAQuery<Long> count = this.findUsersCount(userDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }


    private JPAQuery<UserProjection> findUsersQuery(UserDto userDto, Pageable pageable) {
        return jpaQueryFactory.select(new QUserProjection(
                    userTb.userId,
                    findTire(),
                    userTb.loginId,
                    userTb.nickName,
                    userTb.name,
                    userTb.boardCount,
                    userTb.trialCount,
                    userTb.commentCount,
                    userTb.snsType,
                    userTb.personalPeriod,
                    userTb.delFlag,
                    userTb.authFlag,
                    userTb.judgeFlag,
                    userTb.status,
                    userTb.regDt
                ))
                .from(userTb)
                .where(
                        userTb.loginId.contains(userDto.getKeyword()).or(userTb.nickName.contains(userDto.getKeyword())),
                        eqStatus(userDto.getStatusType()),
                        eqSnsType(userDto.getSnsType())
                )
                .orderBy(orderType(userDto.getOrderType()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    private JPAQuery<Long> findUsersCount(UserDto userDto) {
        return jpaQueryFactory.select(userTb.count())
                .from(userTb)
                .leftJoin(tierTb).on(tierTb.tierId.eq(userTb.userId))
                .where(
                        userTb.loginId.contains(userDto.getKeyword()).or(userTb.nickName.contains(userDto.getKeyword()))
                );
    }

    private JPAQuery<String> findTire() {
        return jpaQueryFactory.select(tierTb.name)
                .from(tierTb)
                .where(
                        tierTb.tierId.eq(userTb.tierId.tierId)
                );
    }

    private BooleanExpression eqStatus(Integer type) {
        switch (type) {
            case 1: return userTb.status.eq(UserStatus.NORMAL_STATUS);
            case 2: return userTb.status.eq(UserStatus.REPORT_STATUS);
            case 3: return userTb.status.eq(UserStatus.DELETE_STATUS);
            default:return null;
        }
    }

    private BooleanExpression eqSnsType(Integer type) {
        switch (type) {
            case 1: return userTb.snsType.eq(SnsType.LG_SNS_TYPE);
            case 2: return userTb.snsType.eq(SnsType.KAKAO_SNS_TYPE);
            case 3: return userTb.snsType.eq(SnsType.NAVER_SNS_TYPE);
            case 4: return userTb.snsType.eq(SnsType.GOOGLE_SNS_TYPE);
            default:return null;
        }
    }

    private OrderSpecifier orderType(Integer type) {
        switch (type) {
            case 0: return userTb.userId.desc();
            case 1: return userTb.boardCount.desc();
            case 2: return userTb.trialCount.desc();
            case 3: return  userTb.commentCount.desc();
            default: return null;
        }
    }
}
