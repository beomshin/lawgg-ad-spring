package com.was.admin.enums.converters.etc;

import com.was.admin.common.crypto.HashNMacUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Converter
@Component
@RequiredArgsConstructor
public class HashConverter  implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        try {
            return HashNMacUtil.getHashSHA256(attribute);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
