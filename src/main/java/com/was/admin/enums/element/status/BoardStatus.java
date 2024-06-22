package com.was.admin.enums.element.status;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardStatus implements LegacyEnum {

    NORMAL_STATUS(1),
    DELETE_STATUS(2),

    REPORT_STATUS(9)

    ;

    int code;

    public static BoardStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
