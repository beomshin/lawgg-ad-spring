package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmDefense implements LegacyEnum {

    NORMAL(0),
    VICTORY(1),
    LOST(2)
    ;

    int code;

    public static LawFirmDefense of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
