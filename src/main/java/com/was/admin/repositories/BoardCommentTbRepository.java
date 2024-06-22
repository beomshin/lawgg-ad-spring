package com.was.admin.repositories;

import com.was.admin.entities.BoardCommentTb;
import com.was.admin.enums.element.status.BoardCommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardCommentTbRepository extends JpaRepository<BoardCommentTb, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET status = :status WHERE boardCommentId = :boardCommentId")
    int updateBoardCommentStatus(@Param("boardCommentId") Long boardCommentId, @Param("status") BoardCommentStatus status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET content = :content WHERE boardCommentId = :boardCommentId")
    int updateBoardComment(@Param("boardCommentId") Long boardCommentId, @Param("content") String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET report = report + 1  WHERE boardCommentId = :boardCommentId")
    int reportBoardComment(@Param("boardCommentId") Long boardCommentId);



}
