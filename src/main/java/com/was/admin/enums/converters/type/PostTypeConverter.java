package com.was.admin.enums.converters.type;

import com.was.admin.enums.converters.AbstractEnumAttributeConverter;
import com.was.admin.enums.element.type.PostType;

public class PostTypeConverter extends AbstractEnumAttributeConverter<PostType> {

    public PostTypeConverter() {
        super(PostType.class);
    }
}
