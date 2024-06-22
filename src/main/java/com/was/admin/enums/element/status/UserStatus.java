package com.was.admin.enums.element.status;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus implements LegacyEnum {

    NORMAL_STATUS(1),
    DELETE_STATUS(9),
    REPORT_STATUS(2);

    int code;

    public static UserStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
