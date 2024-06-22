package com.was.admin.enums.element.status;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmApplyStatus implements LegacyEnum {

    APPLY_STATUS(0),
    END_STATUS(1),
    CANCEL_STATUS(2)
    ;

    int code;

    public static LawFirmApplyStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
