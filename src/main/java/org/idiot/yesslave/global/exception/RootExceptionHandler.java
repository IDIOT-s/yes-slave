package org.idiot.yesslave.global.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.Problem;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.zalando.problem.Status.BAD_REQUEST;
import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class RootExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
            @Content(schema = @Schema(implementation = Problem.class))
    })
    public ResponseEntity<Problem> exceptionHandler(Exception e) {

        log.error("[ 500 ERROR ] : ", e);

        Problem problem = Problem.builder()
                .withStatus(INTERNAL_SERVER_ERROR)
                .withTitle(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .withDetail(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problem);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(schema = @Schema(implementation = InvalidResponse.class))
    })
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[ 400 ERROR ] : ", e);

        BindingResult bindingResult = e.getBindingResult();

        List<InvalidResponse> responses = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            responses.add(new InvalidResponse(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue()));
        });

        Problem problem = Problem.builder()
                .withStatus(BAD_REQUEST)
                .withTitle(BAD_REQUEST.getReasonPhrase())
                .with("parameters", responses)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(schema = @Schema(implementation = InvalidResponse.class))
    })
    @ExceptionHandler(ConstraintViolationException.class)
    public Problem constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error("[ 400 ERROR ] : ", e);

        List<InvalidResponse> responses = new ArrayList<>();

        e.getConstraintViolations().forEach(fieldError -> {
            responses.add(new InvalidResponse(fieldError.getPropertyPath().toString(), fieldError.getMessage(), fieldError.getInvalidValue()));
        });

        return Problem.builder()
                .withStatus(BAD_REQUEST)
                .withTitle(BAD_REQUEST.getReasonPhrase())
                .with("parameters", responses)
                .build();
    }
}
