package com.applc.library.services;

import java.util.List;

import com.applc.library.entities.Book;

public interface BookService {
	Book save(Book book);
	Book update(Book book);
	String delete(Integer bookId);
	List<Book> findAll();
	Book findOne(Integer bookId);
	Book findByISBN(String ISBN);
}
