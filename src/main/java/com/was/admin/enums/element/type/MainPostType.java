package com.was.admin.enums.element.type;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MainPostType implements LegacyEnum{

    NORMAL_TYPE(0),
    MAIN_POST_TYPE(1),
    ;

    int code;

    public static MainPostType of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
