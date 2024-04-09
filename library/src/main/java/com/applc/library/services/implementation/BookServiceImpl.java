package com.applc.library.services.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.applc.library.entities.Book;
import com.applc.library.repositories.BookRepository;
import com.applc.library.services.BookService;
import com.applc.library.services.exceptions.BookAlreadyExistsException;
import com.applc.library.services.exceptions.BookDoesNotExistException;

public class BookServiceImpl implements BookService{
	 private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	    @Autowired
	    private BookRepository bookRepository;

	@Override
	@Transactional
	public Book save(Book book) {
    	LOGGER.debug("Creating {}", book);
    	if(!bookRepository.findByBookISBN(book.getBookISBN()).isEmpty()) {
    		throw new BookAlreadyExistsException(
    				String.format("There is already a book by the name of [%s]", book.getBookTitle()));
    	}
    	return bookRepository.save(book);

	}

	@Override
	@Transactional
	public Book update(Book book) {
    	LOGGER.debug("Updating {}", book);
    	if(bookRepository.findById(book.getBookId()) == null) {
    		throw new BookDoesNotExistException(
    				String.format("There is no book by the name of [%s]", book.getBookTitle()));
    	}
    	return bookRepository.save(book);
	}

	@Override
	@Transactional
	public String delete(Integer bookId) {
    	LOGGER.debug("Deleting book {}", bookRepository.findById(bookId));
    	if(bookRepository.findById(bookId) == null) {
    		throw new BookDoesNotExistException(
    				String.format("There is no book by the name of [%s]", bookRepository.findById(bookId).get().getBookTitle()));
    	}
    	
    	bookRepository.deleteById(bookId);
    	return(String.format("Book with id [%s] has been successfully deleted!", bookId));
	}

	@Override
	@Transactional
	public List<Book> findAll() {
    	LOGGER.debug("Finding all books currently registered");

		return (List<Book>) bookRepository.findAll();
	}

	@Override
	@Transactional
	public Book findOne(Integer bookId) {
    	LOGGER.debug("Finding a book with book id {}", bookId);
		if(bookRepository.findById(bookId).get() == null) {
			throw new BookDoesNotExistException(
					String.format("There is no book with book id=%d", bookId));
		}
		return bookRepository.findById(bookId).get();
	}

	@Override
	@Transactional
	public Book findByISBN(String ISBN) {
    	LOGGER.debug("Finding a book with book ISBN {}", ISBN);
		if(bookRepository.findByBookISBN(ISBN).get() == null) {
			throw new BookDoesNotExistException(
					String.format("There is no book with ISBN=%d", ISBN));
		}
		return bookRepository.findByBookISBN(ISBN).get();
	}

}
