package com.was.admin.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ResponseDto {

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date time = new Date(); // 날짜
    public String resultCode = GlobalCode.SYSTEM_ERROR.getCode();
    public String resultMsg = GlobalCode.SYSTEM_ERROR.getMsg();

    public Boolean success = true;

    public void setError(GlobalCode globalCode) {
        this.resultCode = globalCode.getCode();
        this.resultMsg = globalCode.getMsg();
        this.success = false;
    }


}
