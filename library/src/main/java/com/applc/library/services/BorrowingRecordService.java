package com.applc.library.services;

import java.util.List;

import com.applc.library.entities.BorrowingRecord;

public interface BorrowingRecordService {
	BorrowingRecord save(BorrowingRecord bRcrd);
	BorrowingRecord update(BorrowingRecord bRcrd);
	List<BorrowingRecord> findAll();
	BorrowingRecord findOne(Integer bRcrdId);
	BorrowingRecord findByBorrowingPatronAndBorrowedBook(Integer patronId, Integer bookId);
}
