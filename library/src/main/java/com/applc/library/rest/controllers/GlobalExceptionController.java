package com.applc.library.rest.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.applc.library.services.exceptions.BookAlreadyExistsException;
import com.applc.library.services.exceptions.BookDoesNotExistException;
import com.applc.library.services.exceptions.BookIsAlreadyBorrowedException;
import com.applc.library.services.exceptions.BorrowingRecordAlreadyExists;
import com.applc.library.services.exceptions.BorrowingRecordDoesNotExistException;
import com.applc.library.services.exceptions.PatronAlreadyExistsException;
import com.applc.library.services.exceptions.PatronDoesNotExistException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
	    LOGGER.info("IllegalArgumentException occured: URL="+request.getRequestURL());
	    return e.getLocalizedMessage();
	}
	
	
	@ExceptionHandler(BookAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleBookAlreadyExistsException(HttpServletRequest request, BookAlreadyExistsException e) {
        LOGGER.info("BookAlreadyExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	
	@ExceptionHandler(BorrowingRecordAlreadyExists.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleBorrowingRecordAlreadyExists(HttpServletRequest request, BorrowingRecordAlreadyExists e) {
        LOGGER.info("BorrowingRecordAlreadyExists occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	@ExceptionHandler(PatronAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handlePatronAlreadyExistsException(HttpServletRequest request, PatronAlreadyExistsException e) {
        LOGGER.info("PatronAlreadyExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	@ExceptionHandler(BookIsAlreadyBorrowedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleBookIsAlreadyBorrowedException(HttpServletRequest request, BookIsAlreadyBorrowedException e) {
        LOGGER.info("BookIsAlreadyBorrowedException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	@ExceptionHandler(BorrowingRecordDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleBorrowingRecordDoesNotExistException(HttpServletRequest request, BorrowingRecordDoesNotExistException e) {
        LOGGER.info("BorrowingRecordDoesNotExistException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	@ExceptionHandler(PatronDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handlePatronDoesNotExistException(HttpServletRequest request, PatronDoesNotExistException e) {
        LOGGER.info("PatronDoesNotExistException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	@ExceptionHandler(BookDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleBookDoesNotExistException(HttpServletRequest request, BookDoesNotExistException e) {
        LOGGER.info("BookDoesNotExistException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        LOGGER.info("ConstraintViolationException occured: URL="+request.getRequestURL());
        return e.getConstraintViolations().iterator().next().getMessage();

	}


}
