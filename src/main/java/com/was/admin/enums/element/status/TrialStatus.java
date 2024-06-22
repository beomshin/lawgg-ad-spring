package com.was.admin.enums.element.status;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrialStatus implements LegacyEnum {

    NORMAL_STATUS(1),
    DELETE_STATUS(2),
    UPLOAD_PROCESS_STATUS(3),
    UPLOAD_FAIL_STATUS(4),

    REPORT_STATUS(9)
    ;

    int code;

    public static TrialStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
