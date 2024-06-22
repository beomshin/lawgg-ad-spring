package com.was.admin.repositories;

import com.was.admin.entities.TierTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierTbRepository extends JpaRepository<TierTb, Long> {

    TierTb findByKey(String key);
}
