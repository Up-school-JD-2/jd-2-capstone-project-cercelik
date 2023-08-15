package io.upschool.ticketSystem.exception;

import io.upschool.ticketSystem.dto.NotOkResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {

        final var errorMessage =
                MessageFormat.format("No handler found for {0} {1}", ex.getHttpMethod(), ex.getRequestURL());
        System.out.println(errorMessage);
        var response = NotOkResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleAll(final CustomException exception, final WebRequest request) {
        System.out.println("Something went wrong Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        var response = NotOkResponse.builder()
                .status(exception.getStatusCode())
                .error(exception.getMessage())
                .build();

        return ResponseEntity.status(exception.getStatusCode()).body(response);

    }


}
