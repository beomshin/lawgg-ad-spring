package com.was.admin.exception;

import com.was.admin.common.dto.GlobalCode;
import lombok.Getter;

@Getter
public class LgException extends Exception {

	private GlobalCode globalCode;

	public LgException(GlobalCode globalCode) {
		super(globalCode.getMsg());
		this.globalCode = globalCode;
	}

}
