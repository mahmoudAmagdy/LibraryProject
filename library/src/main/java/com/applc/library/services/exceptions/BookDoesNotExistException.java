package com.applc.library.services.exceptions;

public class BookDoesNotExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public BookDoesNotExistException(final String message) {
        super(message);
    }

}
