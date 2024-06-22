package com.was.admin.enums.element.type;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WriterType implements LegacyEnum {

    ANONYMOUS_TYPE(0),
    MEMBER_TYPE(1),
    LAW_FIRM_TYPE(2)
    ;

    int code;

    public static WriterType of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }

}
