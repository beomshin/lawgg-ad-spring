package com.was.admin.enums.element.flag;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DelFlag implements LegacyEnum {

    NON_DELETE_STATUS(0),
    DELETE_STATUS(1)

    ;

    int code;

    public static DelFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
