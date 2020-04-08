package com.logistics.cloud.exception;

import com.logistics.cloud.eunm.ResultCode;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 3034121940056795549L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BusinessException() {

    }

    public BusinessException(ResultCode resultCode){
        super(resultCode.message());
        this.code = resultCode.code();
    }
}
