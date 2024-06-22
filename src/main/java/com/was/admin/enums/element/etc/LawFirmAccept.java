package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmAccept implements LegacyEnum {


    WAIT(0),
    ACCEPT(1),
    NON_ACCEPT(2)
    ;

    int code;

    public static LawFirmAccept of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
