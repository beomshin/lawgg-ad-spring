package com.was.admin.enums.converters.etc;

import com.was.admin.common.crypto.AESCrypt;
import com.was.admin.common.crypto.KeyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter
@Component
@RequiredArgsConstructor
public class AESCryptConverter implements AttributeConverter<String, String> {

    private final AESCrypt aesCrypt;

    @Override
    public String convertToDatabaseColumn(String s) {
        if (StringUtils.isBlank(s)) return null;
        try {
            return aesCrypt.encrypt(s, KeyManager.aesKey);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if (StringUtils.isBlank(s)) return null;
        try {
            return aesCrypt.decrypt(s, KeyManager.aesKey);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}
