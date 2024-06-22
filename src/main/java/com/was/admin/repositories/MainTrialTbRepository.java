package com.was.admin.repositories;

import com.was.admin.entities.MainTrialTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MainTrialTbRepository extends JpaRepository<MainTrialTb, Long> {

    MainTrialTb findByTrialId(@Param("trialId") Long trialId);
}
