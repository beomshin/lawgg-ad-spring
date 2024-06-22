package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrialTopic implements LegacyEnum{

    ALL_TOPIC(0),
    LIVE_TOPIC(1),
    ;

    int code;

    public static TrialTopic of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
