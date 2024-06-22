package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmSubject implements LegacyEnum{

    ALL_TYPE(0),
    LAW_FIRM_NAME_TYPE(1),
    REP_NAME_TYPE(2),

    ;

    int code;

    public static LawFirmSubject of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
