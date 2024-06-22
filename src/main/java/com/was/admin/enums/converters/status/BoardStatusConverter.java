package com.was.admin.enums.converters.status;

import com.was.admin.enums.converters.AbstractEnumAttributeConverter;
import com.was.admin.enums.element.status.BoardStatus;

public class BoardStatusConverter extends AbstractEnumAttributeConverter<BoardStatus> {

    public BoardStatusConverter() {
        super(BoardStatus.class);
    }
}
