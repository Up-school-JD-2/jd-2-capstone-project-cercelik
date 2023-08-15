package io.upschool.ticketSystem.exception;

public class ResourceNotFoundException extends CustomException {

    private static final int statusCode = 404;
    private static final String msg = "Resource not found";

    public ResourceNotFoundException() {
        super(msg, statusCode);
    }
}
