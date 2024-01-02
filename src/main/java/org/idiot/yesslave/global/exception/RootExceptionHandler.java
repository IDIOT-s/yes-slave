package org.idiot.yesslave.global.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
//@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class RootExceptionHandler implements ProblemHandling {
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
}
