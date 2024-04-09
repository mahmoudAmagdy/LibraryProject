package com.applc.library.services.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.applc.library.entities.BorrowingRecord;
import com.applc.library.repositories.BookRepository;
import com.applc.library.repositories.BorrowingRecordRepository;
import com.applc.library.repositories.PatronRepository;
import com.applc.library.services.BorrowingRecordService;
import com.applc.library.services.exceptions.BorrowingRecordAlreadyExists;
import com.applc.library.services.exceptions.BorrowingRecordDoesNotExistException;

import jakarta.transaction.Transactional;

public class BorrowingRecordServiceImpl implements BorrowingRecordService{
	 private static final Logger LOGGER = LoggerFactory.getLogger(BorrowingRecordServiceImpl.class);

	@Autowired
	private BorrowingRecordRepository bRcrdRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PatronRepository patronRepository;
	
	@Override
	@Transactional
	public BorrowingRecord save(BorrowingRecord bRcrd) {
    	LOGGER.debug("Creating {}", bRcrd);

        	if(bRcrd.getBorrowedBook().getBorrowed()) {
        		throw new BorrowingRecordAlreadyExists(
        				String.format("The book with title [%s] is already borrowed", bRcrd.getBorrowedBook().getBookTitle()));
        	}
    	return bRcrdRepository.save(bRcrd);
	}

	@Override
	@Transactional
	public BorrowingRecord update(BorrowingRecord bRcrd) {
    	LOGGER.debug("Updating borrowing status of record: {}", bRcrd);
    	if(bRcrdRepository.findById(bRcrd.getBRecId()) == null) {
    		throw new BorrowingRecordDoesNotExistException(
    				String.format("There is no record for the borrowed books in relation to the returning patron [%s]", bRcrd.getBorrowingPatron()));
    	}
    	BorrowingRecord bDone = new BorrowingRecord();
    	bDone = bRcrd;
    	bDone.setComplete(true);
    	return bRcrdRepository.save(bDone);
    	
	}

	@Override
	@Transactional
	public List<BorrowingRecord> findAll() {
    	LOGGER.debug("Showing all currently available borrowing records");
    	return (List<BorrowingRecord>) bRcrdRepository.findAll();
	}

	@Override
	@Transactional
	public BorrowingRecord findOne(Integer bRcrdId) {
    	LOGGER.debug("Finding a borrowing with borrowing id {}", bRcrdId);
		if(bRcrdRepository.findById(bRcrdId).get() == null) {
			throw new BorrowingRecordDoesNotExistException(
					String.format("There is no borrowing record with id=%d", bRcrdId));
		}
		return bRcrdRepository.findById(bRcrdId).get();
	}

	@Override
	@Transactional
	public BorrowingRecord findByBorrowingPatronAndBorrowedBook(Integer patronId, Integer bookId) {
    	LOGGER.debug("Finding a borrowing with the borrower's id {} and the book's id {}", patronId, bookId);
		if(bRcrdRepository.findByBorrowingPatronAndBorrowedBook(patronRepository.findById(patronId).get(),bookRepository.findById(bookId).get()).isEmpty()) {
			throw new BorrowingRecordDoesNotExistException(
					String.format("There is no borrowing record with book and patron ids provided =%d", patronId));
		}
		return bRcrdRepository.findByBorrowingPatronAndBorrowedBook(patronRepository.findById(patronId).get(),bookRepository.findById(bookId).get()).get();
	}


}
