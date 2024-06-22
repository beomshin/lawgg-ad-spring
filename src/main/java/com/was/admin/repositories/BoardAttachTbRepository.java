package com.was.admin.repositories;

import com.was.admin.entities.BoardAttachTb;
import com.was.admin.enums.element.status.BoardAttachStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardAttachTbRepository extends JpaRepository<BoardAttachTb, Long> {

    List<BoardAttachTb> findByBoardId_BoardId(@Param("boardId") Long boardId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardAttachTb SET status = :status  WHERE boardAttachId in (:deleteFiles)")
    int deleteFiles(@Param(value = "status")BoardAttachStatus status, List<Long> deleteFiles);
}
