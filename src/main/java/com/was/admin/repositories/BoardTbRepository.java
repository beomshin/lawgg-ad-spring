package com.was.admin.repositories;

import com.was.admin.entities.BoardTb;
import com.was.admin.entities.UserTb;
import com.was.admin.enums.element.status.BoardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

public interface BoardTbRepository extends JpaRepository<BoardTb, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET status = :status WHERE boardId = :boardId")
    int updateBoardStatus(@Param("boardId") Long boardId, @Param("status") BoardStatus status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET title = :title, content = :content  WHERE boardId = :boardId")
    int updateBoard(@Param("boardId") Long boardId, @Param("title") String title, @Param("content") String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET report = report + 1  WHERE boardId = :boardId")
    int reportBoard(@Param("boardId") Long boardId);

    @Modifying
    @Query(value = "UPDATE BoardTb SET view = view + 1  WHERE boardId = :boardId")
    int viewBoard(@Param("boardId") Long boardId);

    long countByBoardIdAndUserTb(Long boardId, UserTb userTb);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT b FROM BoardTb b where b.boardId = :boardId")
    BoardTb findLockBoard(@Param("boardId") Long boardId);
}
