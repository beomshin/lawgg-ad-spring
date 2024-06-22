package com.was.admin.repositories;

import com.was.admin.entities.NickNameTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NickNameTbRepository extends JpaRepository<NickNameTb, Long> {

    Optional<NickNameTb> findByName(String nickName);
}
