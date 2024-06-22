package com.was.admin.enums.element.flag;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JudgeFlag implements LegacyEnum {

    NON_USE_STATUS(0),
    USE_STATUS(1)
    ;

    int code;

    public static JudgeFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
