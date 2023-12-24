package org.idiot.yesslave.notice.exception;

import java.util.Collections;

public class NoSuchNoticeException extends RuntimeException{
    public NoSuchNoticeException() {
        super();
    }

    public NoSuchNoticeException(String message) {
        super(message);
    }

}
