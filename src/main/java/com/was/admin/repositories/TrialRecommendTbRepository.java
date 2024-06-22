package com.was.admin.repositories;

import com.was.admin.entities.TrialRecommendTb;
import com.was.admin.entities.TrialTb;
import com.was.admin.entities.UserTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TrialRecommendTbRepository extends JpaRepository<TrialRecommendTb, Long> {

    long countByTrialTb_TrialIdAndUserTb(Long trialId, UserTb userTb);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM TrialRecommendTb where trialTb = :trialTb and userTb = :userTb")
    int deleteRecommendTrial(@Param("trialTb") TrialTb trialTb, @Param("userTb") UserTb userTb);
}
