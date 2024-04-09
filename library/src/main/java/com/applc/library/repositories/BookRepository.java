package com.applc.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applc.library.entities.Book;


public interface BookRepository extends JpaRepository<Book, Integer>{
	Optional<Book> findByBookISBN(String bookISBN);

}
