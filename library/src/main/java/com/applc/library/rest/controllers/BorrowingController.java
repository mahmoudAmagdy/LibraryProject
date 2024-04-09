package com.applc.library.rest.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applc.library.entities.Book;
import com.applc.library.entities.BorrowingRecord;
import com.applc.library.rest.responseDTOs.BorrowingRecordResponse;
import com.applc.library.services.BookService;
import com.applc.library.services.BorrowingRecordService;
import com.applc.library.services.PatronService;

@RestController
public class BorrowingController {
	 protected static final Logger LOGGER = LoggerFactory.getLogger(BorrowingController.class);

	 @Autowired
	 private BookService bookService;
	 @Autowired
	 private PatronService patronService;
	 @Autowired
	 private BorrowingRecordService bRecService;
	 @Autowired
	 protected ModelMapper pojoMapper;

	 @PostMapping(value="api/borrow/{bookId}/patron/{patronId}")
	    public ResponseEntity<BorrowingRecordResponse> borrowABook (@PathVariable Integer bookId, @PathVariable Integer patronId) {
	        LOGGER.debug("Received request from patron with id {} to borrow the book with id {}", bookId, patronId);
	        BorrowingRecord bRec = new BorrowingRecord();
	        bRec.setBorrowedBook(bookService.findOne(bookId));
	        bRec.setBorrowingPatron(patronService.findOne(patronId));
	        bRec.setComplete(false);
	        BorrowingRecord bb = bRecService.save(bRec);
	        Book book = new Book();
	        book = bookService.findOne(bookId);
	        book.setBorrowed(true);
	        bookService.update(book);
	        return ResponseEntity.ok(pojoMapper.map(bb, BorrowingRecordResponse.class));
	 }
	 
	 @PutMapping(value="/api/return/{bookId}/patron/{patronId}")
	    public String returnABook (@PathVariable Integer bookId, @PathVariable Integer patronId) {
	        LOGGER.debug("Received request from patron with id {} to return the book with id {}", bookId, patronId);
	        Book book = new Book();
	        book = bookService.findOne(bookId);
	        book.setBorrowed(false);
	        bookService.update(book);
	        BorrowingRecord bRec = new BorrowingRecord();
	        bRec = bRecService.findByBorrowingPatronAndBorrowedBook(patronId, bookId);
	        bRec.setComplete(true);
	        bRecService.save(bRec);
	        return("The book has been returned.");
	 }

}
