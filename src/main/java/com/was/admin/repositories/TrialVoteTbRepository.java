package com.was.admin.repositories;

import com.was.admin.entities.TrialTb;
import com.was.admin.entities.TrialVoteTb;
import com.was.admin.entities.UserTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrialVoteTbRepository extends JpaRepository<TrialVoteTb, Long> {

    Optional<TrialVoteTb> findByTrialTbAndUserTb(TrialTb trialTb, UserTb userTb);

    Optional<TrialVoteTb> findByTrialTb_TrialIdAndUserTb(Long trialId, UserTb userTb);
}
