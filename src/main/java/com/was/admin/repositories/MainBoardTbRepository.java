package com.was.admin.repositories;

import com.was.admin.entities.MainBoardTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MainBoardTbRepository extends JpaRepository<MainBoardTb, Long> {

    MainBoardTb findByBoardId(@Param("boardId") Long boardId);
}
