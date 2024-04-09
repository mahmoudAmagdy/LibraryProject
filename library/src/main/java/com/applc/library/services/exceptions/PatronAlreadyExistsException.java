package com.applc.library.services.exceptions;

public class PatronAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public PatronAlreadyExistsException(final String message) {
        super(message);
    }

}
