package io.upschool.ticketSystem.exception;

public class CardNumberIsNotValidException extends CustomException{

    private static int statusCode = 400;
    public CardNumberIsNotValidException(String message) {
        super(message, statusCode);
    }
}
