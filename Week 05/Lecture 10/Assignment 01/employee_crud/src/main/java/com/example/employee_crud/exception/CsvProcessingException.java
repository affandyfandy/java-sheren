package com.example.employee_crud.exception;

public class CsvProcessingException extends RuntimeException {
    public CsvProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
