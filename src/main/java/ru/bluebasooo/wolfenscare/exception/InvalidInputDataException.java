package ru.bluebasooo.wolfenscare.exception;

public class InvalidInputDataException extends RuntimeException {
    public InvalidInputDataException(String invalidDataForCreating) {
        super(invalidDataForCreating);
    }
}
