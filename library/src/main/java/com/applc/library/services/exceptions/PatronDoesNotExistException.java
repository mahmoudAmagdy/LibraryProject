package com.applc.library.services.exceptions;

public class PatronDoesNotExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public PatronDoesNotExistException(final String message) {
        super(message);
    }

}
