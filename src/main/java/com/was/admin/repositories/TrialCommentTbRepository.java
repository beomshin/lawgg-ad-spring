package com.was.admin.repositories;

import com.was.admin.entities.TrialCommentTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TrialCommentTbRepository extends JpaRepository<TrialCommentTb, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialCommentTb SET report = report + 1  WHERE trialCommentId = :trialCommentId")
    int reportTrialComment(@Param("trialCommentId") Long trialCommentId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialCommentTb SET content = :content WHERE trialCommentId = :trialCommentId")
    int updateTrialComment(@Param("trialCommentId") Long trialCommentId, @Param("content") String content);
}
