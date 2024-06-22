package com.was.admin.enums.element.flag;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReplyFlag implements LegacyEnum {

    NON_REPLY_FLAG(0),
    REPLY_FLAG(1)
    ;

    int code;

    public static ReplyFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
