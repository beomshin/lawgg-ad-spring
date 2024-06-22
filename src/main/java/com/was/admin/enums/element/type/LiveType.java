package com.was.admin.enums.element.type;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LiveType implements LegacyEnum {

    NON_LIVE_TYPE(0),
    LIVE_TYPE(1)
    ;

    int code;

    public static LiveType of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
