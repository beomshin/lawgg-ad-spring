package com.was.admin.repositories;

import com.was.admin.entities.AdminTb;
import com.was.admin.entities.UserTb;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminTbRepository extends JpaRepository<AdminTb, Long> {

    Optional<AdminTb> findByLoginId(String loginId);

    Optional<AdminTb> findByAdminId(Long adminId);
}
