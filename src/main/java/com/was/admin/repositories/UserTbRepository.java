package com.was.admin.repositories;

import com.was.admin.entities.LawFirmTb;
import com.was.admin.entities.UserTb;
import com.was.admin.enums.element.flag.JudgeFlag;
import com.was.admin.enums.element.status.UserStatus;
import com.was.admin.enums.element.type.SnsType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

public interface UserTbRepository extends JpaRepository<UserTb, Long> {
    Optional<UserTb> findByLoginId(String loginId);
    Optional<UserTb> findByNickName(String nickName);
    Optional<UserTb> findByLoginIdAndHashEmail(String loginId, String email);
    Optional<UserTb> findBySnsIdAndSnsType(@Param("snsId") String snsId, @Param("snsType") SnsType snsType);

    @EntityGraph(attributePaths = {"tierId", "lawFirmId"})
    Optional<UserTb> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserTb SET lawFirmId = null, lawFirmEnrollDt = null  WHERE userId = :userId")
    int deleteLawFirm(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserTb SET lawFirmId = :lawFirmId, lawFirmEnrollDt = :lawFirmEnrollDt  WHERE userId = :userId")
    int updateLawFirm(@Param("userId") Long userId, @Param("lawFirmId")LawFirmTb lawFirmId, @Param("lawFirmEnrollDt") Timestamp lawFirmEnrollDt);


    @Transactional
    @Modifying
    @Query(value = "UPDATE UserTb SET judgeFlag = :judgeFlag  WHERE userId = :userId")
    int updateJudge(@Param("userId") Long userId, @Param("judgeFlag") JudgeFlag judgeFlag);


    @Transactional
    @Modifying
    @Query(value = "UPDATE UserTb SET status = :status  WHERE userId = :userId")
    int updateStatus(@Param("userId") Long userId, @Param("status") UserStatus status);
}
