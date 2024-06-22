package com.was.admin.repositories;

import com.was.admin.entities.TrialTb;
import com.was.admin.entities.UserTb;
import com.was.admin.enums.element.status.TrialStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

public interface TrialTbRepository extends JpaRepository<TrialTb, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET status = :status WHERE trialId = :trialId")
    int updateTrialStatus(@Param("trialId") Long trialId, @Param("status") TrialStatus status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET report = report + 1  WHERE trialId = :trialId")
    int reportTrial(@Param("trialId") Long trialId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET view = view + 1  WHERE trialId = :trialId")
    int viewTrial(@Param("trialId") Long trialId);

    TrialTb findByTrialIdAndUserTb(Long trialId, UserTb userTb);

    long countByTrialIdAndUserTb(Long trialId, UserTb userTb);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT * FROM TrialTb b where b.trialId = :trialId", nativeQuery = true)
    TrialTb findLockTrial(@Param("trialId") Long trialId);
    
    @Modifying
    @Query(value = "UPDATE TrialTb SET mainPostType = 0  WHERE mainPostType = 1")
    void updateTrialUnHotAll(@Param("trialId") Long trialId);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET mainPostType = 1  WHERE trialId = :trialId")
    void updateTrialHot(@Param("trialId") Long trialId);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialTb SET mainPostType = 0  WHERE trialId = :trialId")
    void updateTrialUnHot(@Param("trialId") Long trialId);
}
