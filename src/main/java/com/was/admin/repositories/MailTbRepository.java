package com.was.admin.repositories;

import com.was.admin.entities.MailTb;
import com.was.admin.enums.element.etc.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface MailTbRepository extends JpaRepository<MailTb, Long> {
    Optional<MailTb> findByCodeAndTxIdAndExpiredAfter(String code, String txId, Date now);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MailTb SET verification = :verification WHERE mailId = :mailId")
    int finishVerification(@Param("mailId") Long mailId, @Param("verification") Verification verification);
}
