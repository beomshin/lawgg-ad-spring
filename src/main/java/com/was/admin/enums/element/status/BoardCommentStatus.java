package com.was.admin.enums.element.status;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardCommentStatus implements LegacyEnum {

    ROOT_STATUS(0),
    NORMAL_STATUS(1),
    DELETE_STATUS(2)
    ;

    int code;

    public static BoardCommentStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
