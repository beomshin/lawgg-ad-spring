package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Precedent implements LegacyEnum {

    PLAINTIFF(0),
    DEFENDANT(1),
    PROCEEDING(9),
    ;

    int code;

    public static Precedent of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
