package com.applc.library.rest.requestDTOs;

import com.applc.library.entities.Book;
import com.applc.library.entities.Patron;

import lombok.Data;

@Data
public class BorrowingRecordRequest {
	private Integer bRecId;
	private Boolean complete;
	private Book borrowedBook;
	private Patron borrowingPatron;

}
