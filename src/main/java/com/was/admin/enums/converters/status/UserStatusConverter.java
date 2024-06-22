package com.was.admin.enums.converters.status;

import com.was.admin.enums.converters.AbstractEnumAttributeConverter;
import com.was.admin.enums.element.status.UserStatus;


public class UserStatusConverter extends AbstractEnumAttributeConverter<UserStatus> {

    public UserStatusConverter() {
        super(UserStatus.class);
    }
}
