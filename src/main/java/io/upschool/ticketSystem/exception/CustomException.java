package io.upschool.ticketSystem.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

public abstract class CustomException extends Exception{
    @Getter
    private final int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
