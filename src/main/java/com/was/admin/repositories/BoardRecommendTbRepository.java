package com.was.admin.repositories;

import com.was.admin.entities.BoardRecommendTb;
import com.was.admin.entities.BoardTb;
import com.was.admin.entities.UserTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRecommendTbRepository extends JpaRepository<BoardRecommendTb, Long> {

    Optional<BoardRecommendTb> findByBoardTb_BoardIdAndUserTb(Long boardId, UserTb userTb);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM BoardRecommendTb where boardTb = :boardTb and userTb = :userTb")
    int deleteRecommendBoard(@Param("boardTb") BoardTb boardTb, @Param("userTb") UserTb userTb);


    long countByBoardTb_BoardIdAndUserTb(Long boardId, UserTb userTb);
}
