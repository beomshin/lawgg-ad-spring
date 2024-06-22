package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Depth implements LegacyEnum {

    ROOT_COMMENT(0),
    PARENT_COMMENT(1),
    CHILDREN_COMMENT(2)
    ;

    int code;

    public static Depth of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
