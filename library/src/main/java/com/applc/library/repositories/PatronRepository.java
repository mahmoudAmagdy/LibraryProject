package com.applc.library.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.applc.library.entities.Patron;

public interface PatronRepository extends JpaRepository<Patron, Integer>{
	
	Optional<Patron> findByPatronEmail(String patronEmail);
	Optional<Patron> findByPatronPhoneNumber(String patronPhoneNumber);
	
	@Query("SELECT j FROM patron j WHERE UPPER(j.patronFirstName) LIKE ?1%") // case insensitive
    List<Patron> findByPatronFirstNameStartingWithIgnoreCase(String patronFirstName, Sort sort);
	
	@Query("SELECT j FROM patron j WHERE UPPER(j.patronLastName) LIKE ?1%") // case insensitive
    List<Patron> findByPatronLastNameStartingWithIgnoreCase(String patronLastName, Sort sort);
}
