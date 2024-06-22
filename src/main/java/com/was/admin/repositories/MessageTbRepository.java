package com.was.admin.repositories;

import com.was.admin.entities.MessageTb;
import com.was.admin.enums.element.flag.ReadFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageTbRepository extends JpaRepository<MessageTb, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE MessageTb SET readFlag = :readFlag WHERE messageId = :messageId")
    int readMessage(@Param("messageId") Long messageId, @Param("readFlag") ReadFlag readFlag);

}
