package com.applc.library.services;

import java.util.List;

import com.applc.library.entities.Patron;


public interface PatronService {
	Patron save(Patron patron);
	Patron findOne(Integer patronId);
	List<Patron> findAll();
	List<Patron> findByPatronFirstNameStartingWith(String patronFirstName);
	List<Patron> findByPatronLastNameStartingWith(String patronLastName);
	Patron update(Patron patron);
	String delete(Integer patronId);

}
