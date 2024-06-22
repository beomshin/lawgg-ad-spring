package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardTopic implements LegacyEnum {

    NEW_TOPIC(0),
    HOT_TOPIC(1),
    ;

    int code;

    public static BoardTopic of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
