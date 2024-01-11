package org.idiot.yesslave.global.exception;

import lombok.Getter;

public class BusinessExceptionHandler extends RuntimeException{
    @Getter
    private final errorCode errorCode;

    public BusinessExceptionHandler(errorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}