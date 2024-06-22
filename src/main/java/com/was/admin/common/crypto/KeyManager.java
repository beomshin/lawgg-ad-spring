package com.was.admin.common.crypto;

import com.was.admin.repositories.ConfigTbRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KeyManager {

    public static String jwtKey;
    public static String aesKey;

    public KeyManager(ConfigTbRepository configTbRepository) {
        this.jwtKey = configTbRepository.findByKey("jwt_key").getValue();
        this.aesKey = configTbRepository.findByKey("aes_key").getValue();
    }
}
