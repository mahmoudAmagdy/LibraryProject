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

import com.applc.library.entities.Patron;
import com.applc.library.rest.requestDTOs.PatronRequest;
import com.applc.library.rest.responseDTOs.PatronResponse;
import com.applc.library.services.PatronService;


@RestController
public class PatronController {
	 protected static final Logger LOGGER = LoggerFactory.getLogger(PatronController.class);
	 
	 @Autowired
	 private PatronService patronService;
	 @Autowired
	 protected ModelMapper pojoMapper;
	 
	    @PostMapping(value="api/patrons")
	    public ResponseEntity<PatronResponse> createPatron(@RequestBody PatronRequest patron) {
	        LOGGER.debug("Received request to create the {}", patron);
	    	Patron savedPat = patronService.save(pojoMapper.map(patron, Patron.class));
	        return ResponseEntity.ok(pojoMapper.map(savedPat,PatronResponse.class));
	    }

	    @GetMapping(value="api/patrons")
	    public ResponseEntity<List<PatronResponse>> getPatrons() {
	        LOGGER.debug("Received request to list all patrons");
	        List<Patron> patrons = new ArrayList<Patron>();
	        patrons = (List<Patron>) patronService.findAll();
	        // convert Patron to PatronResponse
	        List<PatronResponse> patronResponse = new ArrayList<PatronResponse>();
	        for (Patron patron : patrons) {
	            patronResponse.add(pojoMapper.map(patron, PatronResponse.class));
	        }
	        return ResponseEntity.ok(patronResponse);
	    }
	    
	    @GetMapping(value="api/patrons/{patronId}")
	    public ResponseEntity<PatronResponse> getPatron(@PathVariable Integer patronId) {
	        LOGGER.debug("Received request to list the patron with id {}", patronId);
	    	return ResponseEntity.ok(pojoMapper.map(patronService.findOne(patronId), PatronResponse.class));
	    }
	    
	    @PutMapping(value="api/patrons/{patronId}")
	    public ResponseEntity<PatronResponse> updatePatron(@PathVariable Integer patronId, @RequestBody PatronRequest patron) {
	        LOGGER.debug("Received request to update the patron with id {}", patronId);
	    	Patron patronOriginal = pojoMapper.map(patron, Patron.class);
	    	patronOriginal.setPatronId(patronId);
	    	return ResponseEntity.ok(pojoMapper.map(patronService.update(patronOriginal), PatronResponse.class));
	    }
	    
	    @DeleteMapping(value="api/patrons/{patronId}")
	    public String deletePatron(@PathVariable Integer patronId) {
	        LOGGER.debug("Received request to delete the patron with id {}", patronId);
	    	patronService.delete(patronId);
	    	return patronId + " is deleted";
	    }



}
