package com.was.admin.enums.converters.etc;

import com.was.admin.enums.converters.AbstractEnumAttributeConverter;
import com.was.admin.enums.element.etc.LawFirmDefense;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

@Slf4j
@Converter
@Component
public class LawFirmDefenseConverter extends AbstractEnumAttributeConverter<LawFirmDefense> {

    public LawFirmDefenseConverter() {
        super(LawFirmDefense.class);
    }

}
