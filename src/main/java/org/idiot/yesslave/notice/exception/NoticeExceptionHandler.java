package org.idiot.yesslave.notice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoticeExceptionHandler {
    @ExceptionHandler(NoSuchNoticeException.class)
    public ResponseEntity<Object> handleNoSuchNotice(NoSuchNoticeException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
