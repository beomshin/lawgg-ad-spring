package com.was.admin.enums.element.etc;

import com.was.admin.enums.element.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardSubject implements LegacyEnum{

    ALL_TYPE(0),
    TITLE_TYPE(1),
    CONTENT_TYPE(2),
    WRITER_TYPE(3)
    ;

    int code;

    public static BoardSubject of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
