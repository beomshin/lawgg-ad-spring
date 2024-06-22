package com.was.admin.repositories;

import com.was.admin.entities.LawFirmApplyTb;
import com.was.admin.enums.element.status.LawFirmApplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LawFirmApplyTbRepository extends JpaRepository<LawFirmApplyTb, Long> {

    int countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(Long lawFirmId, Long userId, LawFirmApplyStatus status);

    @Modifying
    @Transactional
    @Query("UPDATE LawFirmApplyTb SET status = :status WHERE lawFirmTb.lawFirmId = :lawFirmId AND userTb.userId = :userId")
    int updateApplyStatus(LawFirmApplyStatus status, Long lawFirmId, Long userId);
}
