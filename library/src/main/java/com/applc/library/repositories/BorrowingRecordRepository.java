package com.applc.library.repositories;


import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.applc.library.entities.Book;
import com.applc.library.entities.BorrowingRecord;
import com.applc.library.entities.Patron;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer>{
    @Query("SELECT i FROM borrowing_record i WHERE i.borrowingPatron= ?1 AND i.borrowedBook =?2")
    Optional<BorrowingRecord> findByBorrowingPatronAndBorrowedBook(Patron borrowingPatron, Book borrowedBook);

}
