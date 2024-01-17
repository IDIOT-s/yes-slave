package org.idiot.yesslave.global.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("유효한 데이터가 없습니다.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
