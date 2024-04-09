package com.applc.library.rest.responseDTOs;

import com.applc.library.entities.Book;
import com.applc.library.entities.Patron;

import lombok.Data;

@Data
public class BorrowingRecordResponse {
	private Integer bRecId;
	private Boolean complete;
	private Book borrowedBook;
	private Patron borrowingPatron;


}
