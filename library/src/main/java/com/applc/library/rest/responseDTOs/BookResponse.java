package com.applc.library.rest.responseDTOs;

import lombok.Data;

@Data
public class BookResponse {
	private Integer bookId;
	private String bookTitle;
	private String bookAuthor;
	private Short bookPublishYr;
	private String bookISBN;
	private Boolean borrowed;

}
