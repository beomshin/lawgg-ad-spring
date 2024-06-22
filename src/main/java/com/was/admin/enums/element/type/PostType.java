package com.was.admin.enums.element.type;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostType implements LegacyEnum{

    NORMAL_TYPE(0),
    IMAGE_TYPE(1),
    RECOMMEND(2),
    BEST_TYPE(3),
    EVENT_TYPE(98),
    NOTICE_TYPE(99),
    ;

    int code;

    public static PostType of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
