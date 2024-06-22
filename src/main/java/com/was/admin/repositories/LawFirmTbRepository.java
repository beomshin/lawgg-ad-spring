package com.was.admin.repositories;

import com.was.admin.entities.LawFirmTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LawFirmTbRepository extends JpaRepository<LawFirmTb, Long> {

    int countByName(@Param("name") String name);
}
