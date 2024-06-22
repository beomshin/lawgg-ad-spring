package com.was.admin.modules.boardService.dao;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.was.admin.entities.BoardTb;
import com.was.admin.enums.element.type.LineType;
import com.was.admin.enums.element.type.PostType;
import com.was.admin.modules.boardService.model.dto.BoardProjection;
import com.was.admin.modules.boardService.model.dto.BoardRequestDto;
import com.was.admin.modules.boardService.model.dto.QBoardProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.was.admin.entities.QBoardTb.boardTb;
import static com.was.admin.entities.QMainBoardTb.mainBoardTb;
import static com.was.admin.entities.QUserTb.userTb;


@Repository
@Slf4j
@RequiredArgsConstructor
public class BoardDao {

    private final JPAQueryFactory jpaQueryFactory;

    private final String DATE_FORMAT_ORDER_BY = "DATE_FORMAT({0}, {1})";


    public Page<BoardProjection> findBoardList(BoardRequestDto boardRequestDto, Pageable pageable) {
        JPAQuery<BoardProjection> content = this.findAllBoardList(boardRequestDto, pageable); // 게시판 content
        JPAQuery<Long> count = this.findAllBoardListCount(boardRequestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    public BoardProjection findBoardDetail(BoardRequestDto boardRequestDto) {
        return jpaQueryFactory.select(new QBoardProjection(
                        boardTb.boardId,
                        boardTb.title,
                        boardTb.content,
                        boardTb.writer,
                        boardTb.ip,
                        boardTb.writeDt,
                        boardTb.postType,
                        boardTb.writerType,
                        boardTb.lineType,
                        boardTb.commentCount,
                        boardTb.recommendCount,
                        boardTb.view,
                        boardTb.report,
                        boardTb.status,
                        boardTb.regDt,
                        this.isMain()
                ))
                .from(boardTb)
                .where(
                        boardTb.boardId.eq(boardRequestDto.getId())
                ).fetchOne();
    }

    public JPAQuery<Long> isMain() {
        return jpaQueryFactory.select(mainBoardTb.count()).from(mainBoardTb).where(mainBoardTb.boardId.eq(boardTb.boardId));
    }



    public JPAQuery<BoardProjection> findAllBoardList(BoardRequestDto boardRequestDto, Pageable pageable) { // 게시판 검색
        StringTemplate writeDt = Expressions.stringTemplate(DATE_FORMAT_ORDER_BY, boardTb.writeDt, ConstantImpl.create("%Y-%m-%d"));

        return jpaQueryFactory.select(new QBoardProjection(
                    boardTb.boardId,
                    boardTb.title,
                    boardTb.content,
                    boardTb.writer,
                    boardTb.ip,
                    boardTb.writeDt,
                    boardTb.postType,
                    boardTb.writerType,
                    boardTb.lineType,
                    boardTb.commentCount,
                    boardTb.recommendCount,
                    boardTb.view,
                    boardTb.report,
                    boardTb.status,
                    boardTb.regDt,
                    this.isMain()
                ))
                .from(boardTb)
                .leftJoin(userTb).on(boardTb.userTb.userId.eq(userTb.userId))
                .where(
                        boardTb.writer.contains(boardRequestDto.getKeyword())
                                        .or(boardTb.title.contains(boardRequestDto.getKeyword())),
                        this.eqLineType(boardRequestDto.getType()), // 라인 타입
                        this.eqPostType(boardRequestDto.getType3())
                )
                .orderBy(boardTb.boardId.desc(), this.getOrder(boardRequestDto.getType2())) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(boardTb.boardId);
    }

    public JPAQuery<Long> findAllBoardListCount(BoardRequestDto boardRequestDto) { // 게시판 총 개수 조회
        return jpaQueryFactory
                .select(boardTb.count()).from(boardTb)
                .where(
                        boardTb.writer.contains(boardRequestDto.getKeyword())
                                .or(boardTb.title.contains(boardRequestDto.getKeyword())),
                        this.eqLineType(boardRequestDto.getType()), // 라인 타입
                        this.eqPostType(boardRequestDto.getType3())
                );
    }

    public BooleanExpression eqLineType(Integer type) {
        switch (type) {
            case 0: return boardTb.lineType.eq(LineType.TOP);
            case 1: return boardTb.lineType.eq(LineType.JUNGLE);
            case 2: return boardTb.lineType.eq(LineType.MID);
            case 3: return boardTb.lineType.eq(LineType.ADD);
            case 4: return boardTb.lineType.eq(LineType.SPT);
            default: return null;
        }
    }


    public BooleanExpression eqPostType(Integer type) {
        switch (type) {
            case 1: return boardTb.postType.eq(PostType.NORMAL_TYPE);
            case 2: return boardTb.postType.eq(PostType.IMAGE_TYPE);
            case 3: return boardTb.postType.eq(PostType.RECOMMEND);
            case 4: return boardTb.postType.eq(PostType.BEST_TYPE);
            case 5: return boardTb.postType.eq(PostType.EVENT_TYPE);
            case 6: return boardTb.postType.eq(PostType.NOTICE_TYPE);
            default: return null;
        }
    }

    public OrderSpecifier getOrder(Integer type) {
        switch (type) {
            case 0: return boardTb.commentCount.desc();
            case 1: return boardTb.recommendCount.desc();
            case 2: return boardTb.view.desc();
            case 3: return boardTb.report.desc();
        }
        return null;
    }
}
