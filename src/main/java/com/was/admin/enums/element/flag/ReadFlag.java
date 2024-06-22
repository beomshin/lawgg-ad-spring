package com.was.admin.enums.element.flag;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReadFlag implements LegacyEnum{

    NON_READ_FLAG(0),
    READ_FLAG(1)
    ;

    int code;

    public static ReadFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
