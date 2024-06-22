package com.was.admin.enums.element.type;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LineType implements LegacyEnum {

    TOP(0),
    JUNGLE(1),
    MID(2),
    ADD(3),
    SPT(4),
    ALL(5)
    ;

    int code;

    public static LineType of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
