package com.was.admin.enums.element.type;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EndingType implements LegacyEnum {

    NON_ENDING_TYPE(0),
    ENDING_TYPE(1)
    ;

    int code;

    public static EndingType of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
