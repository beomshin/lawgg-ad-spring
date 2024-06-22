package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmUserSubject implements LegacyEnum{

    ALL_TYPE(0),
    NAME_TYPE(1)
    ;

    int code;

    public static LawFirmUserSubject of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
