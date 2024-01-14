package org.idiot.yesslave.global.exception;

import lombok.Getter;

public class TodoIdHandler extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    public TodoIdHandler(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}