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
import org.zalando.problem.Status;

import javax.validation.ConstraintViolationException;
import java.util.List;

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
            .withStatus(Status.INTERNAL_SERVER_ERROR)
            .withTitle(Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
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

        List<InvalidResponse> responses = bindingResult.getFieldErrors().stream()
            .map(fieldError -> new InvalidResponse(fieldError.getField()
                , fieldError.getDefaultMessage()
                , fieldError.getRejectedValue())
            ).toList();

        Problem problem = Problem.builder()
            .withStatus(Status.BAD_REQUEST)
            .withTitle(Status.BAD_REQUEST.getReasonPhrase())
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

        List<InvalidResponse> responses = e.getConstraintViolations().stream()
            .map(fieldError -> new InvalidResponse(fieldError.getPropertyPath().toString()
                , fieldError.getMessage()
                , fieldError.getInvalidValue())
            ).toList();

        return Problem.builder()
            .withStatus(Status.BAD_REQUEST)
            .withTitle(Status.BAD_REQUEST.getReasonPhrase())
            .with("parameters", responses)
            .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
        @Content(schema = @Schema(implementation = Problem.class))
    })
    public ResponseEntity<Problem> notFoundExceptionHandler(Exception e) {

        log.error("[ 404 ERROR ] : ", e);

        Problem problem = Problem.builder()
            .withStatus(Status.NOT_FOUND)
            .withTitle(Status.NOT_FOUND.getReasonPhrase())
            .withDetail(e.getMessage())
            .build();

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problem);
    }
}
