package com.applc.library.services.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.applc.library.entities.Patron;
import com.applc.library.repositories.PatronRepository;
import com.applc.library.services.PatronService;
import com.applc.library.services.exceptions.PatronAlreadyExistsException;
import com.applc.library.services.exceptions.PatronDoesNotExistException;

public class PatronServiceImpl implements PatronService{
	 private static final Logger LOGGER = LoggerFactory.getLogger(PatronServiceImpl.class);
	    @Autowired
	    private PatronRepository patronRepository;

	@Override
	@Transactional
	public Patron save(Patron patron) {
    	LOGGER.debug("Creating {}", patron);
    	if(!patronRepository.findByPatronEmail(patron.getPatronEmail()).isEmpty()) {
    		System.out.println(patronRepository.findByPatronEmail(patron.getPatronEmail()));
    		throw new PatronAlreadyExistsException(
    				String.format("There is already a patron by the name of [%s]", patron.getPatronFirstName() + " " + patron.getPatronLastName()));
    	}
    	return patronRepository.save(patron);

	}

	@Override
	@Transactional
	public Patron findOne(Integer patronId) {
    	LOGGER.debug("Finding a patron with patron id {}", patronId);
		if(patronRepository.findById(patronId).isEmpty()) {
			throw new PatronDoesNotExistException(
					String.format("There is no patron with job id=%d", patronId));
		}
		return patronRepository.findById(patronId).get();
	}

	@Override
	@Transactional
	public List<Patron> findAll() {
    	LOGGER.debug("Finding all patrons currently registered");

		return (List<Patron>) patronRepository.findAll();

	}

	@Override
	@Transactional
	public List<Patron> findByPatronFirstNameStartingWith(String patronFirstName) {
    	LOGGER.debug("Finding a patron with patron id {}", patronFirstName);
		if(patronRepository.findByPatronFirstNameStartingWithIgnoreCase(patronFirstName, Sort.by(Sort.Direction.ASC,"patronFirstName")) == null) {
			throw new PatronDoesNotExistException(
					String.format("There is no patron with job id=%d", patronFirstName));
		}
		return patronRepository.findByPatronFirstNameStartingWithIgnoreCase(patronFirstName, Sort.by(Sort.Direction.ASC,"patronFirstName"));
	}
	@Override
	@Transactional
	public List<Patron> findByPatronLastNameStartingWith(String patronLastName) {
    	LOGGER.debug("Finding a patron with patron id {}", patronLastName);
		if(patronRepository.findByPatronFirstNameStartingWithIgnoreCase(patronLastName, Sort.by(Sort.Direction.ASC,"patronLastName")) == null) {
			throw new PatronDoesNotExistException(
					String.format("There is no patron with job id=%d", patronLastName));
		}
		return patronRepository.findByPatronFirstNameStartingWithIgnoreCase(patronLastName, Sort.by(Sort.Direction.ASC,"patronLastName"));
	}


	@Override
	@Transactional
	public Patron update(Patron patron) {
    	LOGGER.debug("Updating {}", patron);
    	if(patronRepository.findById(patron.getPatronId()) == null) {
    		throw new PatronDoesNotExistException(
    				String.format("There is no patron by the name of [%s]", patron.getPatronFirstName() + " " + patron.getPatronLastName()));
    	}
    	return patronRepository.save(patron);
	}

	@Override
	@Transactional
	public String delete(Integer patronId) {
    	LOGGER.debug("Deleting patron {}", patronRepository.findById(patronId));
    	if(patronRepository.findById(patronId).isEmpty()) {
    		throw new PatronDoesNotExistException(
    				String.format("There is no patron by the id of [%s]", patronId));
    	}
    	patronRepository.deleteById(patronId);
    	return(String.format("Patron with id [%s] has been successfully deleted!", patronId));
	}

}
