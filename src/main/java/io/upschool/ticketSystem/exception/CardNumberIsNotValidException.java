package io.upschool.ticketSystem.exception;

public class CardNumberIsNotValidException extends CustomException{

    private static final int statusCode = 400;

    private static final String msg = "Card Number must be 16 digits long";
    public CardNumberIsNotValidException(String message) {
        super(message, statusCode);
    }

    public CardNumberIsNotValidException() {
        super(msg, statusCode);
    }
}
