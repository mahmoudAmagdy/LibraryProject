package com.applc.library.services.exceptions;

public class BorrowingRecordAlreadyExists extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public BorrowingRecordAlreadyExists(final String message) {
        super(message);
    }

}
