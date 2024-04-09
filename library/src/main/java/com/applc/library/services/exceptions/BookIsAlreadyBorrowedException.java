package com.applc.library.services.exceptions;

public class BookIsAlreadyBorrowedException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public BookIsAlreadyBorrowedException(final String message) {
        super(message);
    }


}
