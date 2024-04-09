package com.applc.library.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.applc.library.entities.Book;
import com.applc.library.rest.requestDTOs.BookRequest;
import com.applc.library.rest.responseDTOs.BookResponse;
import com.applc.library.services.BookService;

@RestController
public class BookController {
	 protected static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	 @Autowired
	 private BookService bookService;
	 @Autowired
	 protected ModelMapper pojoMapper;
	 
	    @PostMapping(value="api/books")
	    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest book) {
	        LOGGER.debug("Received request to create the {}", book);
	        Book savedBook = pojoMapper.map(book, Book.class);
	        savedBook.setBorrowed(false);
	        bookService.save(savedBook);
	        return ResponseEntity.ok(pojoMapper.map(savedBook,BookResponse.class));
	    }

	    @GetMapping(value="api/books")
	    public ResponseEntity<List<BookResponse>> getBooks() {
	        LOGGER.debug("Received request to list all books");
	        List<Book> books = new ArrayList<Book>();
	        books = (List<Book>) bookService.findAll();
	        // convert Book to BookResponse
	        List<BookResponse> bookResponse = new ArrayList<BookResponse>();
	        for (Book book : books) {
	            bookResponse.add(pojoMapper.map(book, BookResponse.class));
	        }
	        return ResponseEntity.ok(bookResponse);
	    }
	    
	    @GetMapping(value="api/books/{bookId}")
	    public ResponseEntity<BookResponse> getBook(@PathVariable Integer bookId) {
	        LOGGER.debug("Received request to list the book with id {}", bookId);
	    	return ResponseEntity.ok(pojoMapper.map(bookService.findOne(bookId), BookResponse.class));
	    }
	    
	    @PutMapping(value="api/books/{bookId}")
	    public ResponseEntity<BookResponse> updateBook(@PathVariable Integer bookId, @RequestBody BookRequest book) {
	        LOGGER.debug("Received request to update the book with id {}", bookId);
	    	Book bookOriginal = pojoMapper.map(book, Book.class);
	    	bookOriginal.setBookId(bookId);
	    	bookOriginal.setBorrowed(bookService.findOne(bookId).getBorrowed());
	    	return ResponseEntity.ok(pojoMapper.map(bookService.update(bookOriginal), BookResponse.class));
	    }
	    
	    @DeleteMapping(value="api/books/{bookId}")
	    public String deleteBook(@PathVariable Integer bookId) {
	        LOGGER.debug("Received request to delete the book with id {}", bookId);
	    	bookService.delete(bookId);
	    	return bookId + " is deleted";
	    }


}
