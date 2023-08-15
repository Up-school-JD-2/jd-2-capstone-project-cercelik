package io.upschool.ticketSystem.exception;

public class ResourceNotFoundException extends CustomException {

    private static int statusCode = 404;
    private static String msg = "Resource not found";

    public ResourceNotFoundException() {
        super(msg, statusCode);
    }
}
