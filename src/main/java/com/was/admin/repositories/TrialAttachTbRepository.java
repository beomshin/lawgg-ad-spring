package com.was.admin.repositories;

import com.was.admin.entities.TrialAttachTb;
import com.was.admin.enums.element.status.TrialAttachStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrialAttachTbRepository extends JpaRepository<TrialAttachTb, Long> {

    List<TrialAttachTb> findByTrialId_TrialId(@Param("trialId") Long trialId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialAttachTb SET status = :status  WHERE trialAttachId in (:deleteFiles)")
    int deleteFiles(@Param(value = "status") TrialAttachStatus status, List<Long> deleteFiles);
}
