package org.idiot.yesslave.global.exception;

import lombok.Getter;

public enum errorCode {
    ID_NOT_FOUND("해당 ID를 찾을 수 없습니다."),
    ID_DELETE("삭제된 ID입니다.");



    @Getter
    private String message;

    errorCode(String message) {
        this.message = message;
    }
}