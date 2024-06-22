package com.was.admin.enums.element.type;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlertType implements LegacyEnum {


    BOARD_ALERT_TYPE(0),
    TRIAL_ALERT_TYPE(1)
    ;

    int code;

    public static AlertType of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
