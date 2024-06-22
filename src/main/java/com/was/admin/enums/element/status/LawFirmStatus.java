package com.was.admin.enums.element.status;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmStatus implements LegacyEnum {

    STOP_STATUS(0),
    NORMAL_STATUS(1),
    DELETE_STATUS(2)
    ;

    int code;

    public static LawFirmStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
