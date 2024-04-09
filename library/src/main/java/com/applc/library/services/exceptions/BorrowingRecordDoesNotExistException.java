package com.applc.library.services.exceptions;

public class BorrowingRecordDoesNotExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public BorrowingRecordDoesNotExistException(final String message) {
        super(message);
    }

}
