package com.was.admin.enums.element.flag;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthFlag implements LegacyEnum {

    NON_AUTH_STATUS(0),
    AUTH_STATUS(1)

    ;

    int code;

    public static AuthFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
